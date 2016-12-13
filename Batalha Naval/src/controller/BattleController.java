package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.AtackType;
import model.Coordinate;
import model.Player;
import model.Weapon;
import model.WeaponType;
import view.BattleFrame;

@SuppressWarnings("serial")
public class BattleController extends BattleFrame implements ActionListener, Observer {

	BattleController() {
		super();
		this.setTitle("Batalha Naval"); 
		this.setVisible(true);
		
		setFirstBoardListener();
		setSecondBoardListener();
		getTurnButton().addActionListener(this);
		
		MenuController menuController = MenuController.getMenuController();
		menuController.createAndShowGUI(this);
		menuController.enableSaving(true);
		menuController.enableReloading(false);

		AtackType[][] firstAtackMatrix = getAtackMatrix(GameController.getMainGameManager().getWaitingPlayer(), GameController.getMainGameManager().getActivePlayer());
		AtackType[][] secondAtackMatrix = getAtackMatrix(GameController.getMainGameManager().getActivePlayer(), GameController.getMainGameManager().getWaitingPlayer());
		getFirstBoardPanel().updateAtackBoard(firstAtackMatrix);
		getSecondBoardPanel().updateAtackBoard(secondAtackMatrix);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameController gameManager = GameController.getMainGameManager();
		
		gameManager.changePlayerTurn();
		Player activePlayer = gameManager.getActivePlayer();
		
		activePlayer.refreshShots();
		this.setInstruction("Vez de " + activePlayer.getName() + ": " + activePlayer.getShotsLeft() + " tiros restantes");
		getTurnButton().setEnabled(false);
		
		Player currentPlayer = gameManager.getActivePlayer();
		Player opponentPlayer = gameManager.getWaitingPlayer();
	
		AtackType[][] firstAtackMatrix = getAtackMatrix(opponentPlayer, currentPlayer);
		AtackType[][] secondAtackMatrix = getAtackMatrix(currentPlayer, opponentPlayer);
		getFirstBoardPanel().updateAtackBoard(firstAtackMatrix);
		getSecondBoardPanel().updateAtackBoard(secondAtackMatrix);
		
		getContentPane().revalidate();
	}
	
	public void setFirstBoardListener() {
		getFirstBoardPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Do Nothing;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AtackType[][] firstAtackMatrix = getAtackMatrix(GameController.getMainGameManager().getWaitingPlayer(), GameController.getMainGameManager().getActivePlayer());
				getFirstBoardPanel().updateAtackBoard(firstAtackMatrix);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
//				getFirstBoardPanel().updateBoardForPlayer(GameController.getMainGameManager().getActivePlayer());
				WeaponType[][] weaponMatrix = getWeaponMatrix(GameController.getMainGameManager().getActivePlayer());
				getFirstBoardPanel().updatePlayerBoard(weaponMatrix);
			}
		});
		
	}
	
	public void setSecondBoardListener() {
		getSecondBoardPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				secondBoardMousePressedHandler(e.getX(), e.getY());
			}
		});
	}
	
	private void secondBoardMousePressedHandler(int x, int y) {
		
		GameController gameManager = GameController.getMainGameManager();
		if(gameManager.getActivePlayer().getShotsLeft() > 0){
			int line = x/25;
			int column = y/25;
			Coordinate selectedCoord = new Coordinate(line, column);
			Player currentPlayer = gameManager.getActivePlayer();
			if(line < 1 || column < 1){
				return;
			}
			onNewAtack(currentPlayer, selectedCoord);
					
			if(gameManager.checkEndOfGame() == true) {
				gameManager.endGame();
			}
			
			if(currentPlayer.getShotsLeft() <= 0){
				getTurnButton().setEnabled(true);
			}
		}		
	}
	
	/**
	 * On Realizing new attack calls the function
	 * @param currentPlayer Player making attack
	 * @param opponentPlayer Victim
	 * @param selectedCoord Selected Attack coordinate
	 */
	public void onNewAtack(Player currentPlayer, Coordinate selectedCoord) {
		
		if(currentPlayer.getAtacks().contains(selectedCoord)) {
			return;
		}
		
		currentPlayer.setNewAtack(selectedCoord);
		
	}
	
	public AtackType[][] emptyAtackMatrix() {
		AtackType[][] atackMatrix =  new AtackType[getSecondBoardPanel().getNumLines()][getSecondBoardPanel().getNumColumns()];
		for (int i = 0; i < getSecondBoardPanel().getNumLines(); i++) {
			for (int j = 0 ; j < getSecondBoardPanel().getNumColumns(); j++) {
				atackMatrix[i][j] = AtackType.empty;
			}
		}
		
		return atackMatrix;
	}
	
	public AtackType[][] getAtackMatrix(Player currentPlayer, Player opponentPlayer) {
		
		ArrayList<Coordinate> atacks = currentPlayer.getAtacks();
		AtackType[][] atackMatrix =  emptyAtackMatrix();
		
		for(Coordinate atack: atacks) {
			Weapon hitWeapon = opponentPlayer.getHitWeapon(atack);
			if(hitWeapon != null) {
				atackMatrix[atack.getX() - 1][atack.getY() - 1] = AtackType.hit;
			}
			else {
				atackMatrix[atack.getX() - 1][atack.getY() - 1] = AtackType.water;
			}
		}
		return atackMatrix;
	}
	
	public WeaponType[][] emptyWeaponMatrix() {
		WeaponType[][] weaponMatrix = new WeaponType[getFirstBoardPanel().getNumLines()][getFirstBoardPanel().getNumColumns()];
		for (int i = 0; i < getFirstBoardPanel().getNumLines(); i++) {
			for (int j = 0 ; j < getFirstBoardPanel().getNumColumns(); j++) {
				weaponMatrix[i][j] = null;
			}
		}
		return weaponMatrix;
	}
	
	public WeaponType[][] getWeaponMatrix(Player player) {
		
		Weapon[] weapons = player.getWeapons();
		WeaponType[][] weaponMatrix = emptyWeaponMatrix();
		
		for(Weapon weapon: weapons) {
			if(weapon != null) {
				Coordinate[] coordinates = weapon.getBoatPositions(weapon.getPosition());
				Coordinate initialCoord = weapon.getInitialCoordinate();
				
				for(Coordinate coord: coordinates) {
					int relativeX = initialCoord.getX() + coord.getX();
					int relativeY = initialCoord.getY() - coord.getY();
					weaponMatrix[relativeX - 1][relativeY - 1] = weapon.getWeaponType();
				}
			}
		}
		return weaponMatrix;
	}

	@Override
	public void update(Observable o, Object arg) {		
		Coordinate selectedCoord;
		if(arg instanceof Coordinate){
			selectedCoord = (Coordinate) arg;
			viewUpdate(selectedCoord);
		} else {
			System.out.println("Observer passing wrong variable type...");
			return;
		}
		
	}
	
	/**
	 * Realizes the view update when the observer calls it.
	 * @param selectedCoord
	 */
	private void viewUpdate(Coordinate selectedCoord){
		Player currentPlayer = GameController.getMainGameManager().getActivePlayer();
		Player opponentPlayer = GameController.getMainGameManager().getWaitingPlayer();
		
		ArrayList<Coordinate> atacks = currentPlayer.getAtacks();
		
		String hitResult = "";
		String playerInstruction = "Vez de " + currentPlayer.getName() + ": " + currentPlayer.getShotsLeft() + " tiros restantes";
		atacks = currentPlayer.getAtacks();
		AtackType[][] atackMatrix =  emptyAtackMatrix();
		
		for(Coordinate atack: atacks) {
			Weapon hitWeapon = opponentPlayer.getHitWeapon(atack);
			if(hitWeapon != null) {
				atackMatrix[atack.getX() - 1][atack.getY() - 1] = AtackType.hit;
				if(atack.equals(selectedCoord)) {
					//if it is the current atack, update hit squares and result label.
					hitWeapon.incrementHitSquares();
					if(hitWeapon.getNumHitSquares() == hitWeapon.getNumSquares()) {
						hitResult = "Afundou " + hitWeapon.getWeaponType().name() + "! ";
						hitWeapon.setSunk(true);
					}
					else {
						hitResult = "Atingiu " + hitWeapon.getWeaponType().name() + " (" + hitWeapon.getNumHitSquares() + "/" + hitWeapon.getNumSquares() + ")" + "! ";
					}
				}
			}
			else {
				atackMatrix[atack.getX() - 1][atack.getY() - 1] = AtackType.water;
				if(atack.equals(selectedCoord)) {
					hitResult = "Atingiu água :( ";					
				}
			}
		}
		
		setInstruction(hitResult + playerInstruction);
		getSecondBoardPanel().updateAtackBoard(atackMatrix);
	}
}
