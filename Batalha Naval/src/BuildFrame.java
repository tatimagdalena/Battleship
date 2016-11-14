//View
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class BuildFrame extends JFrame implements ActionListener{
	
	private GameBoard boardPanel = new GameBoard();
	private JPanel instructionPanel = new JPanel();
	private JButton turnButton = new JButton("Pronto!");
	
	public BuildFrame() {
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		setSize(screen.screenIntWidth, screen.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		setLayout(null);
		
		instructionPanel.setSize(500, 40);
		instructionPanel.setLocation((int)(screen.screenIntWidth*1/2 - instructionPanel.getSize().getWidth()/2), 
				(int) 50);
		
		boardPanel.setSize(boardPanel.getNumLines()*boardPanel.getSquareSize(), 
				boardPanel.getNumColumns()*boardPanel.getSquareSize());
		boardPanel.setLocation((int)(screen.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2), 
				(int)(screen.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2));
		
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
	
	public JPanel getInstrction(){
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
		game.changePlayerTurn();
	}
}
