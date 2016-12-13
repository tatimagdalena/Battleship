package view;


import javax.swing.*;

import controller.GameController;
import model.Player;
import utils.ScreenDimensions;

@SuppressWarnings("serial")
public class BattleFrame extends JFrame {

	private GameBoard firstBoardPanel = new GameBoard(); 
	private GameBoard secondBoardPanel = new GameBoard(); 
	private JButton turnButton = new JButton("Proximo jogador!");
	private JLabel instructionPanel = new JLabel();
	
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
		//setFirstBoardListener();
		
		secondBoardPanel.setSize(boardWidth, boardHeight);
		secondBoardPanel.setLocation((int)(screen.screenIntWidth*3/4 - secondBoardPanel.getSize().getWidth()/2), 
				yBoardPosition);
		//secondBoardPanel.updateBoardForPlayer(gameManager.getPlayer2());
		//setSecondBoardListener();
		
		int buttonWidth = 150;
		int buttonHeight = 50;
		int yButtonPosition = (int)(yBoardPosition + boardHeight + 30);
		turnButton.setSize(buttonWidth, buttonHeight);
		turnButton.setLocation((int)(screen.screenIntWidth*1/2 - buttonWidth/2), yButtonPosition);
		turnButton.setEnabled(false);
		
		getContentPane().add(instructionPanel);
		getContentPane().add(firstBoardPanel);
		getContentPane().add(secondBoardPanel);
		getContentPane().add(turnButton);
		
		
		//repaint();
	}
	
	public void setInstruction (String text){
		instructionPanel.setText(text);
	}
		
	public GameBoard getFirstBoardPanel() {
		return firstBoardPanel;
	}
	
	public GameBoard getSecondBoardPanel() {
		return secondBoardPanel;
	}
	
	public JButton getTurnButton() {
		return turnButton;
	}
	
	public JLabel getInstructionPanel() {
		return instructionPanel;
	}
}
