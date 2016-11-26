import java.awt.Color;

public class Player {

	final PlayerTurn turn;
	final String name;
	private GameBoard board = new GameBoard();
	
	public Player(String name, PlayerTurn turn) {
		this.turn = turn;
		this.name = name;

	}
	
	public GameBoard getBoard(){
		return board;	
	}
	
	public void setBoard(GameBoard board){
		this.board = board;
	}
}
