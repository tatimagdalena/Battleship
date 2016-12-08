package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import controller.GameController;
import model.Coordinate;
import model.Player;
import model.PlayerTurn;
import utils.ScreenDimensions;

@SuppressWarnings("serial")
public class BattleFrame extends JFrame {

	private GameBoard firstBoardPanel = new GameBoard(); 
	private GameBoard secondBoardPanel = new GameBoard(); 
	private JButton turnButton = new JButton("Começar Jogo!");
	
	public BattleFrame() {
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		setSize(screen.screenIntWidth, screen.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		setLayout(null);
		
		int boardWidth = (firstBoardPanel.getNumColumns() + 1) * firstBoardPanel.getSquareSize();
		int boardHeight = (firstBoardPanel.getNumLines() + 1) * firstBoardPanel.getSquareSize();
		int yBoardPosition = (int)(screen.screenIntHeight*1/2 - boardHeight/2);
		int xFirstBoardPosition = (int)(screen.screenWidth/4 - boardWidth/2);
		
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
		//turnButton.addActionListener(this);
		
		getContentPane().add(firstBoardPanel);
		getContentPane().add(secondBoardPanel);
		getContentPane().add(turnButton);
		
		//repaint();
	}
	
	public void setFirstBoardListener() {
		firstBoardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				firstBoardMousePressedHandler(e.getX(), e.getY());
			}
		});
	}
	
	private void firstBoardMousePressedHandler(int x, int y) {
		
		GameController gameManager = GameController.getMainGameManager();
		
		if(gameManager.getActivePlayer().getTurn() == PlayerTurn.second) {
			int line = x/25;
			int column = y/25;
			Coordinate selectedCoord = new Coordinate(line, column);
			System.out.println("line "+line+"column "+column);
			Player currentPlayer = gameManager.getActivePlayer();
			currentPlayer.setNewAtack(selectedCoord);
			
			Player opponentPlayer = gameManager.getWaitingPlayer();
			
			firstBoardPanel.updateAtackBoardForPlayer(currentPlayer, opponentPlayer);
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
		
		if(gameManager.getActivePlayer().getTurn() == PlayerTurn.first) {
			int line = x/25;
			int column = y/25;
			Coordinate selectedCoord = new Coordinate(line, column);
			System.out.println("line "+line+"column "+column);
			Player currentPlayer = gameManager.getActivePlayer();
			currentPlayer.setNewAtack(selectedCoord);
			
			Player opponentPlayer = gameManager.getWaitingPlayer();
			
			secondBoardPanel.updateAtackBoardForPlayer(currentPlayer, opponentPlayer);
		}
		//else: player clicking on his own board: do nothing.
		
	}
}
