package controller;

import model.*;
import view.*;

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
	public void checkEndOfGame(Game game) {
		
	}
	
}
