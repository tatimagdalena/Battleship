package controller;

import model.*;
import view.*;

public class GameController {

	private static GameController gameManager = null;
	
	private Game game = new Game();
	
	private NamingFrame playersNamingFrame;
	private BuildFrame positioningFrame;
	private BattleFrame battleFrame;
	
	private GameController() {
	}
	
	public static GameController getMainGameManager() {
		if(gameManager == null) {
			gameManager = new GameController();
		}
		return gameManager;
	}
	
	public void setPlayer1(String name) {
		game.setPlayer1(name);
	}
	
	public void setPlayer2(String name) {
		game.setPlayer2(name);
	}
	
	public Player getPlayer1() {
		return game.getPlayer1();
	}
	
	public Player getPlayer2() {
//		return player2;
		return game.getPlayer2();
	}
	
	public Player getActivePlayer(){
		return game.getActivePlayer();
	}
	
	public Player getWaitingPlayer() {
		return game.getWaitingPlayer();
	}
	
	public void initiateGame() { 
		playersNamingFrame = new NamingFrame();
		playersNamingFrame.setTitle("Batalha Naval"); 
		playersNamingFrame.setVisible(true);
	}
	
	public void changePlayerTurn(){
		if(game.getActiveTurn() == PlayerTurn.first) {
			setActivePlayer(PlayerTurn.second);
		} else {
			setActivePlayer(PlayerTurn.first);
		}
		
	}
	
	public void closeNaming() {
		playersNamingFrame.setVisible(false);
	}
	
	public void showPositioning() {
		positioningFrame = new BuildController();
		setActivePlayer(PlayerTurn.first);
	}
	
	public void closePositioning() {
		positioningFrame.setVisible(false);
	}
	
	public BuildFrame getPositioningFrame() {
		return positioningFrame;
	}
	
	public void showBattle() {
		battleFrame = new BattleController();
		battleFrame.setTitle("Batalha Naval");
		battleFrame.setVisible(true);
		game.setActiveTurn(PlayerTurn.first);
	}
	
	private void setActivePlayer(PlayerTurn player){
		game.setActiveTurn(player);
		
	}
	
	public void checkEndOfGame(Game game) {
		
	}
	
}
