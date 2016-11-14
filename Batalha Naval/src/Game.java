//Controller

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game {

	private static Game game = null;
	
	private Player player1;
	private Player player2;
	
	private NamingFrame playersNamingFrame = new NamingFrame();
	private WindowFrame positioningFrame = new WindowFrame();
	
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
	
	public void initiateGame() { 
		playersNamingFrame.setTitle("Batalha Naval"); 
		playersNamingFrame.setVisible(true);
	}
	
	public void closeNaming() {
		playersNamingFrame.setVisible(false);
	}
	
	public void showPositioning() {
		
		positioningFrame.setTitle("Batalha Naval"); 
		positioningFrame.setVisible(true);
		
		positioningFrame.getPanel().addMouseListener(new MouseAdapter() {// provides empty implementation of all
            // MouseListener`s methods, allowing us to
            // override only those which interests us
			@Override //I override only one method for presentation
			public void mousePressed(MouseEvent e) {
			System.out.println(e.getX() + "," + e.getY());
			int line = (e.getX() - 500)/25;
			int column = (e.getY() - 100)/25;
			System.out.println(line + "," + column);
			}
		});
	}
	
	
	
}
