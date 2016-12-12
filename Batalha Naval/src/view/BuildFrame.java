
package view;
//
import java.awt.*;
import javax.swing.*;

import controller.*;
import model.*;
import model.weapons.*;
import utils.*;

@SuppressWarnings("serial")
public class BuildFrame extends JFrame  {
	
	private GameBoard boardPanel;
	private JPanel instructionPanel = new JPanel();
	private JButton turnButton = new JButton("Pronto!");
	private Hidroaviao[] hidroaviao = new Hidroaviao[5];
	private Destroyer[] destroyer = new Destroyer[3];
	private Submarino[] submarino = new Submarino[4];
	private Cruzador[] cruzador = new Cruzador[2];
	private Couracado[] couracado = new Couracado[1];
	private Weapon[] boat = new Weapon[15];
	private WeaponView[] boatView = new WeaponView[15];
	
	private int setWeaponsAmount = 0;
	
	private Weapon activeBoat = null;
	private Point currentMousePosition = new Point(0,0);
	
	private int baseX = 50;
	
	public int basePointX;
	public int basePointY;
	
	public BuildFrame() {
		//Window configuration
		final ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		setSize(screen.screenIntWidth, screen.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLayout(null);
		setFocusable(true);
		requestFocusInWindow();
		
		//Panel de Intruções ao jogador
		instructionPanel.setSize(500, 100);
		//instructionPanel.lines
		instructionPanel.setLocation((int)(screen.screenIntWidth*1/2 - instructionPanel.getSize().getWidth()/2), 
				(int) 50);
		this.setInstruction("<html>Vez de " + GameController.getMainGameManager().getPlayer1().getName() +
				": <br> Botão direito do mouse gira a peça, esquerdo seleciona. <br> Clique no tabuleiro para colocar no local desejado. </html>");
		
		
		//Board
		boardPanel = new GameBoard();
		boardPanel.setSize((boardPanel.getNumLines()+1)*boardPanel.getSquareSize(), 
						(boardPanel.getNumColumns()+1)*boardPanel.getSquareSize());
		basePointX = (int)(screen.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2);
		basePointY = (int)(screen.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2);
		
		boardPanel.setLocation(basePointX, basePointY);	
		
		//Weapons
		drawWeaponsInitialList();
		
		//Done button
		turnButton.setSize(100, 50);
		turnButton.setLocation((int)(screen.screenIntWidth*1/2 - turnButton.getSize().getWidth()/2), 
				(int)(boardPanel.getLocation().getY() + boardPanel.getSize().getHeight() + 30));
		turnButton.setEnabled(false);
		
		getContentPane().add(instructionPanel);
		getContentPane().add(boardPanel);
		getContentPane().add(turnButton);
	}
	
	public JButton getTurnButton() {
		return turnButton;
	}
	
	public GameBoard getPanel() {
		return boardPanel;
	}
	
	public Weapon getActiveBoat() {
		return activeBoat;
	}
	
	public void setActiveBoat(Weapon activeBoat) {
		this.activeBoat = activeBoat;
	}
	
	public Point getCurrentMousePosition() {
		return currentMousePosition;
	}
	
	public void setCurrentMousePosition(Point p) {
		this.currentMousePosition = p;
	}
	
	public Weapon[] getBoat() {
		return boat;
	}
	
	public WeaponView getWeaponView(int i) {
		return boatView[i];
	}
	
	public JPanel getInstruction(){
		return instructionPanel;
	}
	
	public void setInstruction (String text){
		instructionPanel.removeAll();
		instructionPanel.revalidate();
		Label instructionLabel = new Label(text);
		instructionPanel.add(instructionLabel);
	}
	
	public void incrementSetWeaponsAmount() {
		setWeaponsAmount++;
	}
	
	public void decrementSetWeaponsAmount() {
		setWeaponsAmount--;
	}
	
	public int getSetWeaponsAmount() {
		return setWeaponsAmount;
	}
	
	public void clearSetWeaponsAmount() {
		setWeaponsAmount = 0;
	}
	
	public void drawWeaponsInitialList() {
		boat = new Weapon[15];
		clearSetWeaponsAmount();
		int boatCounter = 0;
		
		/*
		 * HIDROAVIOES
		 * Create the class
		 * setSizeBased on class variables
		 * setLocation relative to the last boat placed
		 * set Opaque Background
		 * add to the screen and to the boat array
		 */
		for (int i = 0; i< 5; i++){
			
			hidroaviao[i] = new Hidroaviao(i);
			WeaponView weaponView = new WeaponView(hidroaviao[i], Color.green);
			weaponView.setSize(weaponView.getWeapon().getMatricialWidth()*25,
					weaponView.getWeapon().getMatricialHeight()*25);
			weaponView.setLocation(baseX + (int)(weaponView.getWeapon().getMatricialWidth()*25 + 25) * i,
					(int)boardPanel.getLocation().getY());
			weaponView.setOpaque(false);
			
			boat[boatCounter] = weaponView.getWeapon();
			boat[boatCounter].setTag(boatCounter);
			
			boatView[boatCounter] = weaponView;
			getContentPane().add(boatView[boatCounter]);
			
			boatView[boatCounter].repaint();
			
			boatCounter++;
		}
		
		/*
		 * DESTROYERS
		 * Create the class
		 * setSizeBased on class variables
		 * setLocation relative to the last boat placed
		 * set Opaque Background
		 * add to the screen and to the boat array
		 */
		for (int i = 0; i< 3; i++){			
			destroyer[i] = new Destroyer(i);
			WeaponView weaponView = new WeaponView(destroyer[i], Color.magenta);
			weaponView.setSize(weaponView.getWeapon().getMatricialWidth()*25,
					weaponView.getWeapon().getMatricialHeight()*25);
			weaponView.setLocation(baseX + (int)(weaponView.getWeapon().getMatricialWidth()*25 + 25) * i,
					(int)(boatView[0].getLocation().getY()) + destroyer[i].getMatricialWidth()*25 + 50);
			weaponView.setOpaque(false);
			
			boat[boatCounter] = weaponView.getWeapon();
			boat[boatCounter].setTag(boatCounter);
			
			boatView[boatCounter] = weaponView;
			getContentPane().add(boatView[boatCounter]);
			
			boatView[boatCounter].repaint();
			
			boatCounter++;
		}
		
		/*
		 * SUBMARINOS
		 * Create the class
		 * setSizeBased on class variables
		 * setLocation relative to the last boat placed
		 * set Opaque Background
		 * add to the screen and to the boat array
		 */
		for (int i = 0; i< 4; i++){
			
			submarino[i] = new Submarino(i);
			WeaponView weaponView = new WeaponView(submarino[i], Color.gray);
			weaponView.setSize(weaponView.getWeapon().getMatricialWidth()*25,
					weaponView.getWeapon().getMatricialHeight()*25);
			weaponView.setLocation(baseX + (int)(weaponView.getWeapon().getMatricialWidth()*25 + 25) * i,
					(int)(boatView[5].getLocation().getY()) + submarino[i].getMatricialWidth()*25 + 25);
			weaponView.setOpaque(false);
			
			boat[boatCounter] = weaponView.getWeapon();
			boat[boatCounter].setTag(boatCounter);
			
			boatView[boatCounter] = weaponView;
			getContentPane().add(boatView[boatCounter]);
			
			boatView[boatCounter].repaint();
			
			boatCounter++;
		}
		
		/*
		 * CRUZADORES
		 * Create the class
		 * setSizeBased on class variables
		 * setLocation relative to the last boat placed
		 * set Opaque Background
		 * add to the screen and to the boat array
		 */
		for (int i = 0; i< 2; i++){
			
			cruzador[i] = new Cruzador(i);
			WeaponView weaponView = new WeaponView(cruzador[i], Color.orange);
			weaponView.setSize(weaponView.getWeapon().getMatricialWidth()*25,
					weaponView.getWeapon().getMatricialHeight()*25);
			weaponView.setLocation(baseX + (int)(weaponView.getWeapon().getMatricialWidth()*25 + 25) * i,
					(int)(boatView[8].getLocation().getY()) + cruzador[i].getMatricialWidth()*25 + 25);
			weaponView.setOpaque(false);
			
			boat[boatCounter] = weaponView.getWeapon();
			boat[boatCounter].setTag(boatCounter);
			
			boatView[boatCounter] = weaponView;
			getContentPane().add(boatView[boatCounter]);
			
			boatView[boatCounter].repaint();
			
			boatCounter++;
		}
		
		/*
		 * COURACADOS
		 * Create the class
		 * setSizeBased on class variables
		 * setLocation relative to the last boat placed
		 * set Opaque Background
		 * add to the screen and to the boat array
		 */
		for (int i = 0; i< 1; i++){
			couracado[i] = new Couracado(i);
			WeaponView weaponView = new WeaponView(couracado[i], Color.pink);
			weaponView.setSize(weaponView.getWeapon().getMatricialWidth()*25,
					weaponView.getWeapon().getMatricialHeight()*25);
			weaponView.setLocation((int)boatView[13].getLocation().getX() + (int)(boatView[13].getSize().getWidth() + 25),
					(int)(boatView[8].getLocation().getY()) + cruzador[i].getMatricialWidth()*25 + 25);
			weaponView.setOpaque(false);
			
			boat[boatCounter] = weaponView.getWeapon();
			boat[boatCounter].setTag(boatCounter);
			
			boatView[boatCounter] = weaponView;
			getContentPane().add(boatView[boatCounter]);
			
			boatView[boatCounter].repaint();
			
			boatCounter++;
		}
	}

	
	
	public Point getPanelPoint(){
		return boardPanel.getLocation();
	}
}
