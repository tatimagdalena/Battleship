package controller;

import java.util.Observer;

import model.*;
import view.*;

public class GamePresenter {

	private static GamePresenter gamePresenter = null;
	
	private NamingFrame playersNamingFrame;
	private BuildController positioningFrame;
	private BattleController battleFrame;
	
	/**
	 * Initializes the facade
	 */
	private GamePresenter() {
	}
	
	/**
	 * If not already initialized: initializes the singleton
	 * as an single instance and returns it
	 * If initialized only returns the instance
	 * @return the instanced Game Controller
	 */
	public static GamePresenter getMainGamePresenter() {
		if(gamePresenter == null) {
			gamePresenter = new GamePresenter();
		}
		return gamePresenter;
	}
	
	/**
	 * Initializes the naming frame and sets it visible
	 */
	public void initiateGame() { 
		playersNamingFrame = new NamingFrame();
		playersNamingFrame.setTitle("Batalha Naval"); 
		playersNamingFrame.setVisible(true);
		GameController.getMainGameManager().setGameStage(GameStage.naming);
	}
	
	/**
	 * Closes naming frame
	 */
	public void closeNaming() {
		playersNamingFrame.setVisible(false);
	}
	
	/**
	 * Set the turn for player1 and initilizes the Positioning frame
	 * to start the board building phase
	 */
	public void showPositioning() {
		GameController.getMainGameManager().setActivePlayer(PlayerTurn.first);
		positioningFrame = new BuildController();
		GameController.getMainGameManager().setGameStage(GameStage.positioning);
	}
	
	/**
	 * Closes the positioning Frame to end board building phase
	 */
	public void closePositioning() {
		positioningFrame.setVisible(false);
	}
	
	/**
	 * Getter for the positioning frame to access data
	 * @return The instance of de BuildFrame
	 */
	public BuildFrame getPositioningFrame() {
		return positioningFrame;
	}
	
	/**
	 * Sets player turn to the first, init the battle frame so players can attack each other.
	 */
	public void showBattle() {
		battleFrame = new BattleController();
		battleFrame.setTitle("Batalha Naval");
		battleFrame.setVisible(true);
		GameController gameManager = GameController.getMainGameManager();
		gameManager.getPlayer1().addObserver((Observer) GamePresenter.getMainGamePresenter().getBattleFrame());
		gameManager.getPlayer2().addObserver((Observer) GamePresenter.getMainGamePresenter().getBattleFrame());

		
		GameController.getMainGameManager().setActivePlayer(PlayerTurn.first);
		GameController.getMainGameManager().setGameStage(GameStage.battle);
	}
	
	/**
	 * Presents battle frame from previously saved game.
	 */
	public void showOngoingBattle() {
		playersNamingFrame.setVisible(false);
		if(positioningFrame !=  null) {
			positioningFrame.setVisible(false);
		}
		battleFrame = new BattleController();
		battleFrame.setTitle("Batalha Naval");
		battleFrame.setVisible(true);
		
		GameController gameManager = GameController.getMainGameManager();
		gameManager.getPlayer1().addObserver((Observer) GamePresenter.getMainGamePresenter().getBattleFrame());
		gameManager.getPlayer2().addObserver((Observer) GamePresenter.getMainGamePresenter().getBattleFrame());
	}
	
	public BattleFrame getBattleFrame() {
		return battleFrame;
	}
	
	public void closeBattle() {
		battleFrame.setVisible(false);
	}
}