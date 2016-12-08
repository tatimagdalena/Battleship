package model;

public class Game {

	private Player player1;
	private Player player2;
	
	private PlayerTurn activePlayer;
	
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
		if(activePlayer == PlayerTurn.first){
			return player1;
		}
		return player2;
	}
	
	public Player getWaitingPlayer(){
		if(activePlayer == PlayerTurn.first){
			return player2;
		}
		return player1;
	}
	
	public PlayerTurn getActiveTurn() {
		return activePlayer;
	}
	
	public void setActiveTurn(PlayerTurn turn) {
		activePlayer = turn;
	}
	
}
