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
		
		Graphics2D g2d=(Graphics2D) g;
		int line, column;
		
		
		for (line=0; line<numLines; line++){
			for (column=0; column<numColumns; column++){
				Rectangle2D rect = new Rectangle2D.Double(squareSize*column,squareSize*line, squareSize, squareSize);
				g.setColor(Color.cyan);
				g2d.fill(rect);
				g.setColor(Color.black);
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