import javax.swing.*;

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
		
		secondBoardPanel.setSize(boardWidth, boardHeight);
		secondBoardPanel.setLocation((int)(screen.screenIntWidth*3/4 - secondBoardPanel.getSize().getWidth()/2), 
				yBoardPosition);
		
		int buttonWidth = 150;
		int buttonHeight = 50;
		int yButtonPosition = (int)(yBoardPosition + boardHeight + 30);
		turnButton.setSize(buttonWidth, buttonHeight);
		turnButton.setLocation((int)(screen.screenIntWidth*1/2 - buttonWidth/2), yButtonPosition);
		//turnButton.addActionListener(this);
		
		getContentPane().add(firstBoardPanel);
		//getContentPane().add(firstBoardPanel.associatedLineCoord());
		//getContentPane().add(firstBoardPanel.associatedColumnCoord());
		getContentPane().add(secondBoardPanel);
		//getContentPane().add(secondBoardPanel.associatedLineCoord());
		//getContentPane().add(secondBoardPanel.associatedColumnCoord());
		getContentPane().add(turnButton);
		
		//repaint();
	}
}
