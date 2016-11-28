import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Submarino extends Boat implements Shape {
	
	private Rectangle2D rect;
		
	Submarino(int tag){
		super(tag);
		setNumPositions(1);
		setNumSquares(1);
		setBoatWidth(1 * 25);
		setBoatHeight(1 * 25);
		setBoatColor(Color.blue);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		rect = new Rectangle2D.Double(0,0, getSquareSize(), getSquareSize());
				
		g.setColor(getBoatColor());
		g2d.fill(rect);	
		
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[1];
		coords[0] = new Coordinate(0,0);
		return coords;
	}

}
