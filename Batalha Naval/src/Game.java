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
	private BuildFrame positioningFrame = new BuildFrame();
	private BattleFrame battleFrame = new BattleFrame();
	
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
		
		positioningFrame.setTitle("Batalha Naval"); 
		positioningFrame.setVisible(true);
		
		int baseX = (int) positioningFrame.getPanel().getLocation().getX();
		int baseY = (int) positioningFrame.getPanel().getLocation().getY();
		
		setActivePlayer(PlayerTurn.first);
		
		positioningFrame.getPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int line = (e.getX())/25;
				int column = (e.getY())/25;
				System.out.println(line + "," + column);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(positioningFrame.getActiveBoat() != null) {
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(positioningFrame.getActiveBoat() != null) {
				}
			}
		});
		
		positioningFrame.getPanel().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				Boat boat = positioningFrame.getActiveBoat();
				
				/*
				 * On moving mouse
				 * Set the boat visible
				 * Change it's location
				 * repaint to redraw on correct position(the new one)
				 */
				if(positioningFrame.getActiveBoat() != null) {
					Point p = new Point(e.getX()/25, e.getY()/25);
					if(!positioningFrame.getCurrentMousePosition().equals(p)) {			
						//Faz algo caso mude de quadrado
						positioningFrame.setCurrentMousePosition(p);
						boat.setVisible(true);
						boat.setLocation(baseX + 25 * (p.x) ,  baseY + 25 * (p.y) - boat.getBoatHeight() + 25);
						boat.repaint();		
					}
				}
			}
		});
		
		
		for(int i = 0; i < 15; i++){
			int out = i;
			Boat boat = positioningFrame.getBoat()[i];
			boat.setFocusable(true); //Para poder escutar eventos do teclado
			boat.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					/*
					 * RIGHT CLICK ON BOAT
					 * Only allows one boat. Only set active if no other is selected
					 */
					if(positioningFrame.getActiveBoat() == null) {
						positioningFrame.setActiveBoat(boat);
						boat.setVisible(false);
						System.out.println(out);
					}
					/*
					 * LEFT-CLICK
					 * only happens if boat is selected
					 * Hold currentLocation for persistence of variables
					 * Change Position
					 * Set new location so it will be placed where it should
					 * repaint to redraw on correct position(the new one)
					 */
					if(SwingUtilities.isRightMouseButton(e)){
						if (boat != null){
							Point location = boat.getLocation();
							location.setLocation(location.getX(), location.getY() + boat.getBoatHeight() - 25);
							boat.nextPosition();
							boat.setSize(boat.getBoatWidth(), boat.getBoatHeight());
							boat.setLocation((int)location.getX(), (int)location.getY() - boat.getBoatHeight() + 25);
							boat.repaint();
						}
				    }
				}
			});
		}
	}
	
	public void closePositioning() {
		positioningFrame.setVisible(false);
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
