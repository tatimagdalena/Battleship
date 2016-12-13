package model;

public class Game {

	private GameStage gameStage;
	
	private Player player1;
	private Player player2;
	
	private PlayerTurn activePlayer;
	
	/**
	 * Initialize and set player 1 information
	 * @param name the player name to be displayed
	 */
	public void setPlayer1(String name) {
		this.player1 = new Player(name, PlayerTurn.first);
	}
	
	/**
	 * Get the player 1 information
	 * @return reference to the player1 object
	 */
	public Player getPlayer1() {
		return player1;
	}
	
	/**
	 * Initialize and set player 2 information
	 * @param name the player name to be displayed
	 */
	public void setPlayer2(String name) {
		this.player2 = new Player(name, PlayerTurn.second);
	}
	
	/**
	 * Get the player 2 information
	 * @return reference to the player2 object
	 */
	public Player getPlayer2() {
		return player2;
	}
	
	/**
	 * Gets the active player based on whose turn it is
	 * @return reference to the active player object
	 */
	public Player getActivePlayer(){
		if(activePlayer == PlayerTurn.first){
			return player1;
		}
		return player2;
	}
	
	/**
	 * Gets the inactive player based on whose turn it is
	 * @return reference to the inactive player object
	 */
	public Player getWaitingPlayer(){
		if(activePlayer == PlayerTurn.first){
			return player2;
		}
		return player1;
	}
	
	/**
	 * defines whoser turn it is
	 * @param turn Enum player turn, first or second
	 */
	public void setActiveTurn(PlayerTurn turn) {
		activePlayer = turn;
	}
	
	/**
	 * Gets the player turn to know who is active 
	 * @return reference to the PlayerTurn object
	 */
	public PlayerTurn getActiveTurn() {
		return activePlayer;
	}
	
	public GameStage getGameStage() {
		return gameStage;
	}
	
	public void setGameStage(GameStage stage) {
		this.gameStage = stage;
	}
	
	@Override
	public String toString() {
		
		String s;
		
		s = 	/*"ETAPA ATUAL:"
				+ gameStage.name()
				+ "\n"
				+ */
				"ATIVO:"
				+ "\n"
				+ getActivePlayer().toString()
				+ "\n" //blank line between players
				+ "ESPERA:"
				+ "\n"
				+ getWaitingPlayer().toString();
		
		return s;
	}
	
}
