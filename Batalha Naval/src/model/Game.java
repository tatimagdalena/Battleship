package model;

public class Game {

	private Player player1;
	private Player player2;
	
	private PlayerTurn active;
	
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
	
	public PlayerTurn getActiveTurn() {
		return active;
	}
	
	public void setActiveTurn(PlayerTurn turn) {
		active = turn;
	}
	
}
