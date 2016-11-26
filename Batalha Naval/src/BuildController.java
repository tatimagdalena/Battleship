import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingUtilities;

public class BuildController extends BuildFrame {
	
	BuildController positioningFrame = this;
	
	Point boatOldPosition;
	
	BuildController(){
		
		positioningFrame.setTitle("Batalha Naval"); 
		positioningFrame.setVisible(true);
		
		int baseX = (int) positioningFrame.getPanel().getLocation().getX();
		int baseY = (int) positioningFrame.getPanel().getLocation().getY();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if(getActiveBoat() != null) {
						getActiveBoat().setVisible(true);
						getActiveBoat().setLocation(boatOldPosition);
						getActiveBoat().repaint();
						setActiveBoat(null);
					}
				}
			}
		});
		
		positioningFrame.getPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int line = (e.getX())/25;
				int column = (e.getY())/25;
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
						positioningFrame.setCurrentMousePosition(p);
						boat.setVisible(true);
						boat.setLocation(baseX + 25 * (p.x) ,  baseY + 25 * (p.y) - boat.getBoatHeight() + 25);
						boat.repaint();		
					}
				}
			}
		});
		
		
		for(int i = 0; i < 15; i++){
			Boat boat = positioningFrame.getBoat()[i];
			boat.setFocusable(true); //Para poder escutar eventos do teclado
			boat.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {					
					/*
					 * RIGHT CLICK ON BOAT
					 * Only allows one boat. Only set active if no other is selected
					 * If one is already selected when placed over board it's set there
					 * calls funciton drawonboard and then delete the boat
					 * repaint the board
					 */
					if(positioningFrame.getActiveBoat() == null) {
						positioningFrame.setActiveBoat(boat);
						boat.setVisible(false);
						boatOldPosition = boat.getLocation();
					} else {
					
						if(SwingUtilities.isLeftMouseButton(e)){
							if (boat != null){
								Point pos = positioningFrame.getCurrentMousePosition();
								if(pos.getX() != 0 && pos.getY() != 0){
									GameBoard board = positioningFrame.getPanel();
									drawBoatOnBoard(boat, board, (int)pos.getX(), (int)pos.getY());
									boat.setVisible(false);
									positioningFrame.remove(boat);
									positioningFrame.setActiveBoat(null);
									board.repaint();
								}
							}
				    	}
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
	
	private void drawBoatOnBoard(Boat boat, GameBoard board, int x, int y){
		Coordinate[] coords = boat.getBoatPositions(boat.getPosition());
		for (int i=0; i< coords.length; i++){
			board.setCoordColor(x + coords[i].getX(), y + coords[i].getY() - (boat.getBoatHeight()-1)/25 , boat.getBoatColor());
		}
		Game.getMainGame().getActivePlayer().setBoard(board);
	}
}
	
