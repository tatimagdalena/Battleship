//View
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BuildFrame extends JFrame implements ActionListener {
	
	private GameBoard boardPanel = new GameBoard();
	private JPanel instructionPanel = new JPanel();
	private JButton turnButton = new JButton("Pronto!");
	private Hidroaviao[] hidroaviao = new Hidroaviao[5];
	private Destroyer[] destroyer = new Destroyer[3];
	private Submarino[] submarino = new Submarino[4];
	private Cruzador[] cruzador = new Cruzador[2];
	private Couracado[] couracado = new Couracado[1];
	private Boat[] boat = new Boat[15];
	
	private Boat activeBoat = null;
	private Point currentMousePosition = new Point(0,0);
	
	private int baseX = 50;
	
	
	public BuildFrame() {
		//Gofigurações de Janela
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		setSize(screen.screenIntWidth, screen.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLayout(null);
		
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if(activeBoat != null) {
						activeBoat.setVisible(true);
						activeBoat = null;
					}
				}
			}
		});
		
		//Panel de Intruções ao jogador
		instructionPanel.setSize(500, 40);
		instructionPanel.setLocation((int)(screen.screenIntWidth*1/2 - instructionPanel.getSize().getWidth()/2), 
				(int) 50);
		
		//Tabuleiro
		boardPanel.setSize((boardPanel.getNumLines()+1)*boardPanel.getSquareSize(), 
						(boardPanel.getNumColumns()+1)*boardPanel.getSquareSize());
		boardPanel.setLocation((int)(screen.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2), 
						(int)(screen.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2));	
		
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
			hidroaviao[i].setSize(hidroaviao[i].getBoatWidth(), hidroaviao[i].getBoatHeight());
			hidroaviao[i].setLocation(baseX + (int)(hidroaviao[i].getBoatWidth() + hidroaviao[i].getSquareSize()) * i, 
					(int)boardPanel.getLocation().getY());
			hidroaviao[i].setOpaque(false);
			getContentPane().add(hidroaviao[i]);
			boat[boatCounter] = hidroaviao[i];
			boat[boatCounter].setTag(boatCounter);
			boatCounter++;
			hidroaviao[i].repaint();
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
			destroyer[i].setSize(destroyer[i].getBoatWidth(), destroyer[i].getBoatHeight());
			destroyer[i].setLocation(baseX + (int)(destroyer[i].getBoatWidth() + destroyer[i].getSquareSize()) * i, 
					(int)(hidroaviao[0].getLocation().getY()) + hidroaviao[0].getBoatHeight() + destroyer[i].getSquareSize());
			destroyer[i].setOpaque(false);;
			getContentPane().add(destroyer[i]);
			boat[boatCounter] = destroyer[i];
			boat[boatCounter].setTag(boatCounter);
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
			submarino[i].setSize(submarino[i].getBoatWidth(), submarino[i].getBoatHeight());
			submarino[i].setLocation(baseX + (int)(submarino[i].getBoatWidth() + submarino[i].getSquareSize()) * i, 
					(int)(destroyer[0].getLocation().getY()) + destroyer[0].getBoatHeight() + submarino[i].getSquareSize());
			submarino[i].setOpaque(false);
			getContentPane().add(submarino[i]);
			boat[boatCounter] = submarino[i];
			boat[boatCounter].setTag(boatCounter);
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
			cruzador[i].setSize(cruzador[i].getBoatWidth(), cruzador[i].getBoatHeight());
			cruzador[i].setLocation(baseX + (int)(cruzador[i].getBoatWidth() + cruzador[i].getSquareSize()) * i, 
					(int)(submarino[0].getLocation().getY()) + submarino[0].getBoatHeight() + cruzador[i].getSquareSize());
			cruzador[i].setOpaque(false);
			getContentPane().add(cruzador[i]);
			boat[boatCounter] = cruzador[i];
			boat[boatCounter].setTag(boatCounter);
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
			couracado[i].setSize(couracado[i].getBoatWidth(), couracado[i].getBoatHeight());
			couracado[i].setLocation(baseX + (int)(couracado[i].getBoatWidth() + couracado[i].getSquareSize()) * i, 
					(int)(cruzador[0].getLocation().getY()) + cruzador[0].getBoatHeight() + couracado[i].getSquareSize());
			couracado[i].setOpaque(false);
			getContentPane().add(couracado[i]);
			boat[boatCounter] = couracado[i];
			boat[boatCounter].setTag(boatCounter);
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
	
	public Boat getActiveBoat() {
		return activeBoat;
	}
	
	public void setActiveBoat(Boat activeBoat) {
		this.activeBoat = activeBoat;
	}
	
	public Point getCurrentMousePosition() {
		return currentMousePosition;
	}
	
	public void setCurrentMousePosition(Point p) {
		this.currentMousePosition = p;
	}
	
	public Boat[] getBoat() {
		return boat;
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
		Game game = Game.getMainGame();
		Player activePlayer = game.getActivePlayer();
		
		if(activePlayer.turn == PlayerTurn.first) {
			game.changePlayerTurn();
		}
		else {
			game.closePositioning();
			game.showBattle();
		}
	}

}
