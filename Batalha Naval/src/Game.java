//Controller

import java.awt.event.*;
import javax.swing.*; 


public class Game {

	private static Game game = null;
	
	private Player player1;
	private Player player2;
	
	private PlayerTurn active;
	
	private NamingFrame playersNamingFrame = new NamingFrame();
	private BuildFrame positioningFrame = new BuildFrame();
	private BattleFrame battleFrame = new BattleFrame();
	
	
	private Game() {
	}
	
	public static Game getMainGame() {
		if(game == null) {
			game = new Game();
		}
		return game;
	}
	
	public void setPlayer1(String name) {
		this.player1 = new Player(name, PlayerTurn.first);
	}
	
	public void setPlayer2(String name) {
		this.player2 = new Player(name, PlayerTurn.second);
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public Player getActivePlayer(){
		if(active == PlayerTurn.first){
			return player1;
		} else {
			return player2;
		}
	}
	
	public void initiateGame() { 
		playersNamingFrame.setTitle("Batalha Naval"); 
		playersNamingFrame.setVisible(true);
	}
	
	public void changePlayerTurn(){
		if(active == PlayerTurn.first){
			setActivePlayer(PlayerTurn.second);
		} else {
			setActivePlayer(PlayerTurn.first);
		}
		
	}
	
	public void closeNaming() {
		playersNamingFrame.setVisible(false);
	}
	
	public void showPositioning() {
		
		positioningFrame.setTitle("Batalha Naval"); 
		positioningFrame.setVisible(true);
		
		setActivePlayer(PlayerTurn.first);
		
		positioningFrame.getPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
			int line = (e.getX())/25;
			int column = (e.getY())/25;
			System.out.println(line + "," + column);
			
			}
		});
		
		for(int i = 0; i < 15; i++){
			int out = i;
			Boat boat = positioningFrame.getBoat()[i];
			boat.setFocusable(true); //Para poder escutar eventos do teclado
			positioningFrame.getBoat()[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//Só permito escolher um navio se não há nenhum atualmente selecionado
					if(positioningFrame.getActiveBoat() == null) {
						positioningFrame.setActiveBoat(boat);
						boat.requestFocusInWindow(); //O elemento que está em foco escuta eventos do teclado
						boat.setVisible(false);
						System.out.println(out);
					}
				}
			});
			positioningFrame.getBoat()[i].addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
				}
				@Override
				public void keyReleased(KeyEvent e) {
				}
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						if(positioningFrame.getActiveBoat() != null) {
							positioningFrame.getActiveBoat().setVisible(true);
							positioningFrame.setActiveBoat(null);
						}
					}
				}
			});
		}
	}
	
	public void closePositioning() {
		positioningFrame.setVisible(false);
	}
	
	public void showBattle() {
		battleFrame.setTitle("Batalha Naval");
		battleFrame.setVisible(true);
	}
	
	private void setActivePlayer(PlayerTurn player){
		active = player;
		if(active == PlayerTurn.first){
			positioningFrame.setInstruction("Vez do Jogador " + player1.name + ", arraste as peças para o tabuleiro");
		} else {
			positioningFrame.setInstruction("Vez do Jogador " + player2.name + ", arraste as peças para o tabuleiro");
		}
	}
	
}
