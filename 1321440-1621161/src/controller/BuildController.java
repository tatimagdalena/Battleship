package controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;

import model.*;
import view.*;

@SuppressWarnings("serial")
public class BuildController extends BuildFrame implements ActionListener {
	
	//BuildController positioningFrame = this;
	
	Point boatOldPosition;
	Point position;
	
	private MenuController menuController = MenuController.getMenuController();
	
	/**
	 * Init the Controller to set the listeners and frame info
	 */
	BuildController(){
		super();
		this.setTitle("Batalha Naval"); 
		this.setVisible(true);
		
		setKeyListener();
		setBoardListeners();
		setWeaponsListeners();
		getTurnButton().addActionListener(this);
		
		menuController.createAndShowGUI(this);
		menuController.enableSaving(false);
		menuController.enableReloading(true);
	}
	
	/**
	 * Transforms absolute coordinate in matricial coordinate
	 * Place the active boat if there is one.
	 * Remove the placed boat if there is some.
	 * @param x x absolute coordinate
	 * @param y y absolute coordinate
	 */
	private void boardMousePressedHandler(int x, int y) {
		int line = x/25;
		int column = y/25;
		Coordinate selectedCoord = new Coordinate(line, column);
		GameController gameManager = GameController.getMainGameManager();
		Player currentPlayer = gameManager.getActivePlayer();
		
		if(getActiveBoat() != null) {
			onPositioningNewWeapon(currentPlayer, selectedCoord);
		}
		else {
			onUndoWeaponPosition(currentPlayer, selectedCoord);
		}
	}
	
	/**
	 * Add the boat to the player weapon array and if all of them are used enables the button to be done.
	 * @param currentPlayer the player who is positioning boats
	 * @param selectedCoord the coordinate where the boat will be placed
	 */
	private void onPositioningNewWeapon(Player currentPlayer, Coordinate selectedCoord) {
		if (currentPlayer.checkValidPosition(getActiveBoat(), selectedCoord)){
			
			if(currentPlayer.getTurn() == PlayerTurn.first && getSetWeaponsAmount() == 0) {
				//first weapon positioned by first player: disable reloading game
				menuController.enableReloading(false);
			}
			
			incrementSetWeaponsAmount();
			if(getSetWeaponsAmount() == 15) {
				getTurnButton().setEnabled(true);
			}
			
			getActiveBoat().setInitialCoordinate(selectedCoord);
			currentPlayer.addWeapon(getActiveBoat());
			
			setActiveBoat(null);
//			getPanel().updateBoardForPlayer(GameController.getMainGameManager().getActivePlayer());
			WeaponType[][] weaponMatrix = getWeaponMatrix(GameController.getMainGameManager().getActivePlayer()); 
			getPanel().updatePlayerBoard(weaponMatrix);
		}
	}
	
	/**
	 * when click on a boat it's removed form player array and put back on the pile to be selected
	 * @param currentPlayer the player positioning boats
	 * @param selectedCoord the coord where the boat was clicked to be removed from
	 */
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
						getTurnButton().setEnabled(false);
						
						System.out.printf("Removeu peca (tag = %d)\n", weapon.getTag());
						getWeaponView(weapon.getTag()).setVisible(true);
//						getPanel().updateBoardForPlayer(GameController.getMainGameManager().getActivePlayer());
						WeaponType[][] weaponMatrix = getWeaponMatrix(GameController.getMainGameManager().getActivePlayer()); 
						getPanel().updatePlayerBoard(weaponMatrix);
						break;
					}
				}
			}	
		}
		
		if(currentPlayer.getTurn() == PlayerTurn.first && getSetWeaponsAmount() == 0) {
			//first weapon positioned by first player: disable reloading game
			menuController.enableReloading(true);
		}
	}
	
	/**
	 * LEFT-CLICK ON BOAT
	 * Hide Selected Boat and sets it as active boat for latter interaction with board.
	 * 
	 * RIGHT-CLICK
	 * Right click on a boat flips it
	 */
	public void setWeaponsListeners() {
		for(int i = 0; i < getBoat().length; i++){
			WeaponView boatView = getWeaponView(i);
			Weapon boat = getBoat()[i];
			boatView.setFocusable(true); //Para poder escutar eventos do teclado
			boatView.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {					
					
					if(getActiveBoat() == null) {
						
						if(SwingUtilities.isLeftMouseButton(e)){
						
							setActiveBoat(boat);
							boatView.setVisible(false);
							boatOldPosition = boatView.getLocation();
						
						}
					} 
					
					
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
	
	public void setBoardListeners() {
		getPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				boardMousePressedHandler(e.getX(), e.getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(getActiveBoat() != null) {
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(getActiveBoat() != null) {
				}
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameController gameManager = GameController.getMainGameManager();
		GamePresenter facade =  GamePresenter.getMainGamePresenter();
		Player activePlayer = gameManager.getActivePlayer();
		
		if(activePlayer.getTurn() == PlayerTurn.first) {
			gameManager.changePlayerTurn();
			this.setInstruction("<html>Vez de " + gameManager.getPlayer2().getName() +
					": <br> Botão direito do mouse gira a peça, esquerdo seleciona. <br> Clique no tabuleiro para colocar no local desejado. </html>");
			
//			getPanel().updateBoardForPlayer(GameController.getMainGameManager().getActivePlayer());
			WeaponType[][] weaponMatrix = getWeaponMatrix(GameController.getMainGameManager().getActivePlayer()); 
			getPanel().updatePlayerBoard(weaponMatrix);
			drawWeaponsInitialList();
			BuildController buildController = (BuildController) facade.getPositioningFrame();
			buildController.setWeaponsListeners();
			clearSetWeaponsAmount();
			getTurnButton().setEnabled(false);
		}
		else {
			gameManager.changePlayerTurn();
			facade.closePositioning();
			facade.showBattle();
		}
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
	
	public WeaponType[][] emptyWeaponMatrix() {
		WeaponType[][] weaponMatrix = new WeaponType[getPanel().getNumLines()][getPanel().getNumColumns()];
		for (int i = 0; i < getPanel().getNumLines(); i++) {
			for (int j = 0 ; j < getPanel().getNumColumns(); j++) {
				weaponMatrix[i][j] = null;
			}
		}
		return weaponMatrix;
	}
	
	public WeaponType[][] getWeaponMatrix(Player player) {
		
		Weapon[] weapons = player.getWeapons();
		WeaponType[][] weaponMatrix = emptyWeaponMatrix();
		
		for(Weapon weapon: weapons) {
			if(weapon != null) {
				Coordinate[] coordinates = weapon.getBoatPositions(weapon.getPosition());
				Coordinate initialCoord = weapon.getInitialCoordinate();
				
				for(Coordinate coord: coordinates) {
					int relativeX = initialCoord.getX() + coord.getX();
					int relativeY = initialCoord.getY() - coord.getY();
					weaponMatrix[relativeX - 1][relativeY - 1] = weapon.getWeaponType();
				}
			}
		}
		return weaponMatrix;
	}
}
