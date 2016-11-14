//View

import javax.swing.*;

@SuppressWarnings("serial")
public class WindowFrame extends JFrame {
	
	private GameBoard panel = new GameBoard();
	
	public WindowFrame() {
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		setSize(screen.screenIntWidth, screen.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		getContentPane().add(panel);
	}
	
	public GameBoard getPanel() {
		return panel;
	}
	
}
