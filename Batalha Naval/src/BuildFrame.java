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
		
		for (int i = 0 ; i < boardPanel.getNumLines(); i++ ){
			JPanel coordLine = new JPanel();
			Label lineLabel = new Label("" + i);
			coordLine.setSize(boardPanel.getSquareSize(), boardPanel.getSquareSize());
			coordLine.setLocation((int)boardPanel.getLocation().getX() - boardPanel.getSquareSize(), (int)boardPanel.getLocation().getY() + (i * boardPanel.getSquareSize()));
			coordLine.add(lineLabel);
			getContentPane().add(coordLine);
		}
		
		for (int j = 0 ; j < boardPanel.getNumLines(); j++ ){
			JPanel coordColumn = new JPanel();
			Label lineLabel = new Label("" + (char)(j+'A'));
			coordColumn.setSize(boardPanel.getSquareSize(), boardPanel.getSquareSize());
			coordColumn.setLocation((int)boardPanel.getLocation().getX() + (j * boardPanel.getSquareSize()), (int)boardPanel.getLocation().getY() - boardPanel.getSquareSize());
			coordColumn.add(lineLabel);
			getContentPane().add(coordColumn);
		}
		
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
