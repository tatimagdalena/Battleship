package view;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import controller.BuildController;
import controller.GameController;
import model.Coordinate;
import model.Player;
import model.PlayerTurn;
import utils.ScreenDimensions;

@SuppressWarnings("serial")
public class BattleFrame extends JFrame implements ActionListener {

	private GameBoard firstBoardPanel = new GameBoard(); 
	private GameBoard secondBoardPanel = new GameBoard(); 
	private JButton turnButton = new JButton("Proximo jogador!");
	private JLabel instructionPanel = new JLabel();
	private Boolean viewBoard = false;
	
	public BattleFrame() {
		
		
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		setSize(screen.screenIntWidth, screen.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		setLayout(null);
		
		int boardWidth = (firstBoardPanel.getNumColumns() + 1) * firstBoardPanel.getSquareSize();
		int boardHeight = (firstBoardPanel.getNumLines() + 1) * firstBoardPanel.getSquareSize();
		int yBoardPosition = (int)(screen.screenIntHeight*1/2 - boardHeight/2);
		int xFirstBoardPosition = (int)(screen.screenWidth/4 - boardWidth/2);
		
		//Panel de Intruções ao jogador
		Player activePlayer = GameController.getMainGameManager().getActivePlayer();
		instructionPanel.setSize(500, 100);
		instructionPanel.setLocation((int)(screen.screenIntWidth*1/2 - instructionPanel.getSize().getWidth()/2), (int) 50);
		instructionPanel.setText("Vez de " + activePlayer.getName() + ": " + activePlayer.getShotsLeft() + " tiros restantes");
		
		//setInstruction("Vez de " + activePlayer.getName() + ": " + activePlayer.getShotsLeft() + " tiros restantes");
		instructionPanel.setDoubleBuffered(true);
		
		firstBoardPanel.setSize(boardWidth, boardHeight);
		firstBoardPanel.setLocation(xFirstBoardPosition, yBoardPosition);
		//firstBoardPanel.updateBoardForPlayer(gameManager.getPlayer1());
		setFirstBoardListener();
		
		secondBoardPanel.setSize(boardWidth, boardHeight);
		secondBoardPanel.setLocation((int)(screen.screenIntWidth*3/4 - secondBoardPanel.getSize().getWidth()/2), 
				yBoardPosition);
		//secondBoardPanel.updateBoardForPlayer(gameManager.getPlayer2());
		setSecondBoardListener();
		
		int buttonWidth = 150;
		int buttonHeight = 50;
		int yButtonPosition = (int)(yBoardPosition + boardHeight + 30);
		turnButton.setSize(buttonWidth, buttonHeight);
		turnButton.setLocation((int)(screen.screenIntWidth*1/2 - buttonWidth/2), yButtonPosition);
		turnButton.setEnabled(false);
		turnButton.addActionListener(this);
		//turnButton.addActionListener(this);
		
		getContentPane().add(instructionPanel);
		getContentPane().add(firstBoardPanel);
		getContentPane().add(secondBoardPanel);
		getContentPane().add(turnButton);
		
		
		//repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameController gameManager = GameController.getMainGameManager();
		
		gameManager.changePlayerTurn();
		Player activePlayer = gameManager.getActivePlayer();
		
		activePlayer.refreshShots();
		this.setInstruction("Vez de " + activePlayer.getName() + ": " + activePlayer.getShotsLeft() + " tiros restantes");
		turnButton.setEnabled(false);
		
		Player currentPlayer = gameManager.getActivePlayer();
		Player opponentPlayer = gameManager.getWaitingPlayer();
	
		
		firstBoardPanel.updateAtackBoardForPlayer(opponentPlayer, currentPlayer);
		secondBoardPanel.updateAtackBoardForPlayer(currentPlayer, opponentPlayer);
		//firstBoardPanel.revalidate();
		//secondBoardPanel.revalidate();
		
		
		getContentPane().revalidate();
	}
	
	public void setFirstBoardListener() {
		firstBoardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//firstBoardMousePressedHandler(e.getX(), e.getY());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				firstBoardPanel.updateAtackBoardForPlayer(GameController.getMainGameManager().getWaitingPlayer(), GameController.getMainGameManager().getActivePlayer());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				firstBoardPanel.updateBoardForPlayer(GameController.getMainGameManager().getActivePlayer());
			}
		});
		
	}
	
	private void firstBoardMousePressedHandler(int x, int y) {
		
		GameController gameManager = GameController.getMainGameManager();
		if(gameManager.getActivePlayer().getShotsLeft() > 0){
			if(gameManager.getActivePlayer().getTurn() == PlayerTurn.second) {
				int line = x/25;
				int column = y/25;
				Coordinate selectedCoord = new Coordinate(line, column);
				//System.out.println("this line "+line+" column "+column);
				Player currentPlayer = gameManager.getActivePlayer();
				currentPlayer.setNewAtack(selectedCoord);
			
				Player opponentPlayer = gameManager.getWaitingPlayer();
			
				firstBoardPanel.updateAtackBoardForPlayer(opponentPlayer, currentPlayer);
			
				if(currentPlayer.getShotsLeft() >= 0){
					turnButton.setEnabled(true);
				}
			}
		}
		//else: player clicking on his own board: do nothing.
		
		
	}
	
	public void setSecondBoardListener() {
		secondBoardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				secondBoardMousePressedHandler(e.getX(), e.getY());
			}
		});
	}
	
	private void secondBoardMousePressedHandler(int x, int y) {
		
		GameController gameManager = GameController.getMainGameManager();
		if(gameManager.getActivePlayer().getShotsLeft() > 0){
			int line = x/25;
			int column = y/25;
			Coordinate selectedCoord = new Coordinate(line, column);
			Player currentPlayer = gameManager.getActivePlayer();
			Player opponentPlayer = gameManager.getWaitingPlayer();
			
			currentPlayer.setNewAtack(selectedCoord);
			setInstruction("Vez de " + currentPlayer.getName() + ": " + currentPlayer.getShotsLeft() + " tiros restantes");
					
			secondBoardPanel.updateAtackBoardForPlayer(currentPlayer, opponentPlayer);
			
			if(currentPlayer.getShotsLeft() <= 0){
				turnButton.setEnabled(true);
			}
		}
		//else: player clicking on his own board: do nothing.
		
	}
	
	public void setInstruction (String text){
		instructionPanel.setText(text);
	}
}
