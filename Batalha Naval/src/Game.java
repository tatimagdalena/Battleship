//Controller

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Game {

	private static Game game = null;
	
	private Player player1;
	private Player player2;
	
	private PlayerTurn active;
	
	private NamingFrame playersNamingFrame = new NamingFrame();
	private BuildFrame positioningFrame; //= new BuildFrame();
	private BattleFrame battleFrame; //= new BattleFrame();
	
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
		active = player;
		if(active == PlayerTurn.first){
			positioningFrame.setInstruction("Vez do Jogador " + player1.name + ", arraste as peças para o tabuleiro");
		} else {
			positioningFrame.setInstruction("Vez do Jogador " + player2.name + ", arraste as peças para o tabuleiro");
		}
	}
	

	
}
