import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Hidroaviao extends Boat implements Shape {
	
	private Rectangle2D rect1;
	private Rectangle2D rect2;
	private Rectangle2D rect3;
	
	Hidroaviao(int tag){
		super(tag);
		
		setNumPositions(4);
		setNumSquares(3);
		setBoatWidth(3 * 25);
		setBoatHeight(2 * 25);
		setBoatColor(Color.green);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		Coordinate[] coords = getBoatPositions(getPosition());
		
		rect1 = new Rectangle2D.Double(getSquareSize() * coords[0].getX(), getSquareSize() * coords[0].getY(), getSquareSize(), getSquareSize());
		rect2 = new Rectangle2D.Double(getSquareSize() * coords[1].getX(), getSquareSize() * coords[1].getY(), getSquareSize(), getSquareSize());
		rect3 = new Rectangle2D.Double(getSquareSize() * coords[2].getX(), getSquareSize() * coords[2].getY(), getSquareSize(), getSquareSize());
	
		
		g.setColor(getBoatColor());
		g2d.fill(rect1);
		g2d.fill(rect2);
		g2d.fill(rect3);
		
	}


	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[3];
		switch (pos){
		case 0:
			coords[0] = new Coordinate(0,1);
			coords[1] = new Coordinate(1,0);
			coords[2] = new Coordinate(2,1);
			break;
		case 1:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,1);
			coords[2] = new Coordinate(0,2);
			break;
		case 2:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,1);
			coords[2] = new Coordinate(2,0);
			break;
		case 3:
			coords[0] = new Coordinate(1,0);
			coords[1] = new Coordinate(0,1);
			coords[2] = new Coordinate(1,2);
			break;
		}
		
		return coords;
	}

}
