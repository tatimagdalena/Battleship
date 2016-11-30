package model;

import view.GameBoard;

public class Player {

	final PlayerTurn turn;
	final String name;
	private GameBoard board = new GameBoard();
	
	public Player(String name, PlayerTurn turn) {
		this.turn = turn;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public PlayerTurn getTurn() {
		return turn;
	}
	
	public GameBoard getBoard(){
		return board;	
	}
	
	public void setBoard(GameBoard board){
		this.board = board;
	}
}
