package controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;

import model.*;
import view.*;

@SuppressWarnings("serial")
public class BuildController extends BuildFrame {
	
	BuildController positioningFrame = this;
	
	Point boatOldPosition;
	Point position;
	
	BuildController(){
		positioningFrame.setTitle("Batalha Naval"); 
		positioningFrame.setVisible(true);
		
		setKeyListener();
		setBoardListeners();
		setWeaponsListeners();
	}
	
	// Mouse handler when clicking on game board
	private void boardMousePressedHandler(int x, int y) {
		int line = x/25;
		int column = y/25;
		Coordinate selectedCoord = new Coordinate(line, column);
		System.out.println("line "+line+"column "+column);
		GameController gameManager = GameController.getMainGameManager();
		Player currentPlayer = gameManager.getActivePlayer();
		
		if(positioningFrame.getActiveBoat() != null) {
			//TODO: TREAT HERE FOR INVALID POSITION****
			onPositioningNewWeapon(currentPlayer, selectedCoord);
		}
		else {
			onUndoWeaponPosition(currentPlayer, selectedCoord);
		}
	}
	
	private void onPositioningNewWeapon(Player currentPlayer, Coordinate selectedCoord) {
		if (currentPlayer.checkValidPosition(positioningFrame.getActiveBoat(), selectedCoord)){
			incrementSetWeaponsAmount();
			if(getSetWeaponsAmount() == 15) {
				turnButton.setEnabled(true);
			}
			
			positioningFrame.getActiveBoat().setInitialCoordinate(selectedCoord);
			currentPlayer.addWeapon(positioningFrame.getActiveBoat());
			
			setActiveBoat(null);
			positioningFrame.getPanel().updateBoardForPlayer(GameController.getMainGameManager().getActivePlayer());
		}
	}
	
	private void onUndoWeaponPosition(Player currentPlayer, Coordinate selectedCoord) {
		for(Weapon weapon: currentPlayer.getWeapons()) {
			if(weapon != null) {
				Coordinate[] coordinates = weapon.getBoatPositions(weapon.getPosition());
				for(Coordinate coord: coordinates) {
					int relativeX = weapon.getInitialCoordinate().getX() + coord.getX();
					int relativeY = weapon.getInitialCoordinate().getY() - coord.getY();
					Coordinate relativeCoord = new Coordinate(relativeX, relativeY);
					if(relativeCoord.equals(selectedCoord)) {
						currentPlayer.removeWeapon(weapon);
						decrementSetWeaponsAmount();
						turnButton.setEnabled(false);
						
						System.out.printf("Removeu peca (tag = %d)\n", weapon.getTag());
						getWeaponView(weapon.getTag()).setVisible(true);
						positioningFrame.getPanel().updateBoardForPlayer(GameController.getMainGameManager().getActivePlayer());
						break;
					}
				}
			}	
		}
	}
	
	//
	public void setWeaponsListeners() {
		for(int i = 0; i < getBoat().length; i++){
			WeaponView boatView = getWeaponView(i);
			Weapon boat = getBoat()[i];
			boatView.setFocusable(true); //Para poder escutar eventos do teclado
			boatView.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {					
					/*
					 * LEFT-CLICK ON BOAT
					 * Only allows one boat. Only set active if no other is selected
					 * If one is already selected when placed over board it's set there
					 * calls funciton drawonboard and then delete the boat
					 * repaint the board
					 */
					if(positioningFrame.getActiveBoat() == null) {
						
						if(SwingUtilities.isLeftMouseButton(e)){
						
							positioningFrame.setActiveBoat(boat);
							boatView.setVisible(false);
							boatOldPosition = boatView.getLocation();
						
						}
					} 
					
					/*
					 * RIGHT-CLICK
					 * only happens if boat is selected
					 * Hold currentLocation for persistence of variables
					 * Change Position
					 * Set new location so it will be placed where it should
					 * repaint to redraw on correct position(the new one)
					 */
					if(SwingUtilities.isRightMouseButton(e)){
						if (boat != null){
							Point location = boatView.getLocation();
							location.setLocation(location.getX(), location.getY() + boat.getMatricialHeight()*25 - 25);
							boat.nextPosition();
							boatView.setSize(boat.getMatricialWidth()*25, boat.getMatricialHeight()*25);
							boatView.setLocation((int)location.getX(), (int)location.getY() - boat.getMatricialHeight()*25 + 25);
							boatView.repaint();
						}
				    }
					
				}
			});
		}
	}
	
	//
	public void setBoardListeners() {
		positioningFrame.getPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				boardMousePressedHandler(e.getX(), e.getY());
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
	}
	
	public void setKeyListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if(getActiveBoat() != null) {
						int tag = getActiveBoat().getTag();
						getWeaponView(tag).setVisible(true);;
						setActiveBoat(null);
					}
				}
			}
		});
	}
}
