package controller;

import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.*;
import model.weapons.*;

public class GameController {

	private static GameController gameManager = null;
	
	private Game game = new Game();
	
	/**
	 * Initializes the gameController
	 */
	private GameController() {
	}
	
	/**
	 * If not already initialized: initializes the singleton
	 * as an single instance and returns it
	 * If initialized only returns the instance
	 * @return the instanced Game Controller
	 */
	public static GameController getMainGameManager() {
		if(gameManager == null) {
			gameManager = new GameController();
		}
		return gameManager;
	}
	
	public GameStage getGameStage() {
		return game.getGameStage();
	}
	
	public void setGameStage(GameStage stage) {
		game.setGameStage(stage);
	}
	
	/**
	 * Save Player 1 data to the singleton
	 * @param name The player name to be displayed
	 */
	public void setPlayer1(String name) {
		game.setPlayer1(name);
	}
	
	/**
	 * Get the player instance saved on the singleton
	 * @return Single instance of the player object
	 */
	public Player getPlayer1() {
		return game.getPlayer1();
	}
	
	/**
	 * Save Player 2 data to the singleton
	 * @param name The player name to be displayed
	 */
	public void setPlayer2(String name) {
		game.setPlayer2(name);
	}
	
	/**
	 * Get the player instance saved on the singleton
	 * @return Single instance of the player object
	 */
	public Player getPlayer2() {
		return game.getPlayer2();
	}
	
	/**
	 * Get the active player instance(based on playerTurn object) saved on the singleton
	 * @return Single instance of the player object
	 */
	public Player getActivePlayer(){
		return game.getActivePlayer();
	}
	
	/**
	 * Get the inactive player instance(based on playerTurn object) saved on the singleton
	 * @return Single instance of the player object
	 */
	public Player getWaitingPlayer() {
		return game.getWaitingPlayer();
	}
	
	/**
	 * changes player turn based on the activePlayer; Set the other one as active
	 * and the former active as inactive
	 */
	public void changePlayerTurn(){
		if(game.getActiveTurn() == PlayerTurn.first) {
			setActivePlayer(PlayerTurn.second);
		} else {
			setActivePlayer(PlayerTurn.first);
		}
		
	}
	
	/**
	 * changes player turn based on the parameter passed
	 * and the other one active as inactive
	 * @param player PlayerTurn Enum object
	 */
	public void setActivePlayer(PlayerTurn player){
		game.setActiveTurn(player);
	}
	
	/**
	 * Check if the game has finished
	 * @param game instance of the game
	 */
	public Boolean checkEndOfGame() {
		
		Player opponentPlayer = getWaitingPlayer();
		
		for(Weapon weapon: opponentPlayer.getWeapons()) {
			if(weapon.isSunk() == false) {
				return false;
			}
		}
 		
		return true;
	}
	
	public void endGame() {

		GamePresenter gamePresenter = GamePresenter.getMainGamePresenter();
		String dialogMessage = game.getActivePlayer().getName() + " venceu!\n";

		JOptionPane.showMessageDialog(gamePresenter.getBattleFrame(), dialogMessage);
		
		game = null;
		game = new Game();
		
		gamePresenter.closeBattle();
		gamePresenter.initiateGame();
	}
	
	public void saveGame() {
		
		String gameString = game.toString();
		
		System.out.println(gameString);
		
		JFileChooser chooser = new JFileChooser();
	    int result = chooser.showSaveDialog(null);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        try {
	            FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
	            fw.write(gameString);
	            fw.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public Boolean reloadGame() {
		JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
        	
        	Scanner scanner = null;
        	
        	try {
        		scanner = new Scanner(new BufferedReader(new FileReader(chooser.getSelectedFile())));
        		readGameSavedData(scanner);

        	} catch (Exception ex) {
        		ex.printStackTrace();
        	} finally {
        		if (scanner != null) { 
        			scanner.close();
        		}
        	}
        	return true;
        }
        return false;
	}
	
	public void readGameSavedData(Scanner scanner) {
			
		game = new Game();
		
		for(int j = 0; j < 2; j ++) {

			String marker = scanner.next(); //"ATIVO:" or "ESPERA:"

			PlayerTurn turn = PlayerTurn.valueOf(scanner.next());
			String trash = scanner.nextLine();
			String name = scanner.nextLine();
			Player p = null;
			if(turn == PlayerTurn.first) {
				game.setPlayer1(name);
			}
			else {
				game.setPlayer2(name);
			}
			if(marker.equals("ATIVO:")) {
				game.setActiveTurn(turn);
				p = game.getActivePlayer();
			}
			else {
				p = game.getWaitingPlayer();
			}

			marker = scanner.next(); //"restam:"

			String remainingShots = scanner.next();
			p.setShots(Integer.parseInt(remainingShots));


			marker = scanner.next(); //"ARMAS:":

			for(int i = 0; i < 15; i++) {
				WeaponType wt = WeaponType.valueOf(scanner.next());
				int t = Integer.parseInt(scanner.next());
				Weapon w = null;
				switch(wt) {
				case couracado: w = new Couracado(t); break;
				case cruzador: w =  new Cruzador(t); break;
				case destroyer: w = new Destroyer(t); break;
				case hidroaviao: w = new Hidroaviao(t); break;
				case submarino: w = new Submarino(t); break;
				default: w = new Weapon(t, WeaponType.generico); break;
				}
				w.setNumHitSquares(Integer.parseInt(scanner.next()));
				w.setPosition(Integer.parseInt(scanner.next()));;
				w.setSunk(Boolean.valueOf(scanner.next()));
				int ix = Integer.parseInt(scanner.next());
				int iy = Integer.parseInt(scanner.next());
				w.setInitialCoordinate(new Coordinate(ix, iy));
				p.addWeapon(w);
			}


			marker = scanner.next(); //"ATAQUES:":

			int atackAmount = Integer.parseInt(scanner.next());
			for(int i = 0; i < atackAmount; i++) {
				int x = Integer.parseInt(scanner.next());
				int y = Integer.parseInt(scanner.next());
				Coordinate atack = new Coordinate(x,y);
				p.appendToAtacks(atack);
			}
		}	
		System.out.println("fim leitura");
	}
	
}
