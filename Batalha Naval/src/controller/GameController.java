package controller;

import model.*;
import view.*;

public class GameController {

	private static GameController gameManager = null;
	
//	private Player player1;
//	private Player player2;
//	
//	private PlayerTurn active;
	
	private Game game = new Game();
	
	private NamingFrame playersNamingFrame = new NamingFrame();
	private BuildFrame positioningFrame; //= new BuildFrame();
	private BattleFrame battleFrame; //= new BattleFrame();
	
	private GameController() {
	}
	
	public static GameController getMainGameManager() {
		if(gameManager == null) {
			gameManager = new GameController();
		}
		return gameManager;
	}
	
	public void setPlayer1(String name) {
//		this.player1 = new Player(name, PlayerTurn.first);
		game.setPlayer1(name);
	}
	
	public void setPlayer2(String name) {
//		this.player2 = new Player(name, PlayerTurn.second);
		game.setPlayer2(name);
	}
	
	public Player getPlayer1() {
//		return player1;
		return game.getPlayer1();
	}
	
	public Player getPlayer2() {
//		return player2;
		return game.getPlayer2();
	}
	
	public Player getActivePlayer(){
//		if(active == PlayerTurn.first){
		if(game.getActiveTurn() == PlayerTurn.first){
			return game.getPlayer1();
		} else {
			return game.getPlayer2();
		}
	}
	
	public void initiateGame() { 
		playersNamingFrame.setTitle("Batalha Naval"); 
		playersNamingFrame.setVisible(true);
	}
	
	public void changePlayerTurn(){
//		if(active == PlayerTurn.first){
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
		battleFrame.setTitle("Batalha Naval");
		battleFrame.setVisible(true);
	}
	
	private void setActivePlayer(PlayerTurn player){
		game.setActiveTurn(player);
		if(game.getActiveTurn() == PlayerTurn.first){
			positioningFrame.setInstruction("Vez do Jogador " + game.getPlayer1().getName() + ", arraste as peças para o tabuleiro");
		} else {
			positioningFrame.setInstruction("Vez do Jogador " + game.getPlayer2().getName() + ", arraste as peças para o tabuleiro");
		}
	}
	
}