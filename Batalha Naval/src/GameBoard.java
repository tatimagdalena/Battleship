import javax.swing.*; 
import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {

	private int squareSize = 25;
	private int numColumns = 16;
	private int numLines = 16;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		//double panelInitialX = screen.screenCenterX;
		//double panelInitialY = screen.screenCenterY - 15 * 25/2;
		
		
		int line, column;
		
		for (line=0; line<numLines; line++){
			for (column=0; column<numColumns; column++){
				Graphics2D g2d=(Graphics2D) g;
				Rectangle2D rect = new Rectangle2D.Double(squareSize*column,squareSize*line, squareSize, squareSize);
				g2d.draw(rect);
			}
		}
	}
	
	public int getSquareSize(){
		return squareSize;
	}
	
	public int getNumLines(){
		return numLines;
	}
	
	public int getNumColumns(){
		return numColumns;
	}
	
}