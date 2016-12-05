
package view;
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.*;
import model.*;
import model.weapons.Couracado;
import model.weapons.Cruzador;
import model.weapons.Destroyer;
import model.weapons.Hidroaviao;
import model.weapons.Submarino;
import utils.*;

@SuppressWarnings("serial")
public class BuildFrame extends JFrame implements ActionListener  {
	
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
	
	private Weapon activeBoat = null;
	private Point currentMousePosition = new Point(0,0);
	
	private int baseX = 50;
	
	public int basePointX;
	public int basePointY;
	
	public BuildFrame() {
		//Gofigurações de Janela
		final ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		setSize(screen.screenIntWidth, screen.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLayout(null);
		
		setFocusable(true);
		requestFocusInWindow();
		
		
		//Panel de Intruções ao jogador
		instructionPanel.setSize(500, 40);
		instructionPanel.setLocation((int)(screen.screenIntWidth*1/2 - instructionPanel.getSize().getWidth()/2), 
				(int) 50);
		
		//Tabuleiro
		boardPanel = new GameBoard();
		boardPanel.setSize((boardPanel.getNumLines()+1)*boardPanel.getSquareSize(), 
						(boardPanel.getNumColumns()+1)*boardPanel.getSquareSize());
		
		basePointX = (int)(screen.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2);
		basePointY = (int)(screen.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2);
		
		boardPanel.setLocation(basePointX, basePointY);	
		
		
		
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
			WeaponView weaponView = new WeaponView(submarino[i], Color.blue);
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
		
		//Botao para finalizar
		turnButton.setSize(100, 50);
		turnButton.setLocation((int)(screen.screenIntWidth*1/2 - turnButton.getSize().getWidth()/2), 
				(int)(boardPanel.getLocation().getY() + boardPanel.getSize().getHeight() + 30));
		turnButton.addActionListener(this);
		
		getContentPane().add(instructionPanel);
		getContentPane().add(boardPanel);
		getContentPane().add(turnButton);
		
		setInstruction("Player turn");

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

	@Override
	public void actionPerformed(ActionEvent e) {
		GameController game = GameController.getMainGameManager();
		Player activePlayer = game.getActivePlayer();
		
		if(activePlayer.getTurn() == PlayerTurn.first) {
			game.changePlayerTurn();
		}
		else {
			game.closePositioning();
			game.showBattle();
		}
	}
	
	public Point getPanelPoint(){
		return boardPanel.getLocation();
	}
}
