import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Couracado extends Boat implements Shape {
	
	private Rectangle2D rect;
	
	public Couracado(int tag){
		super(tag);
		
		setNumPositions(2);
		setNumSquares(5);
		setBoatWidth(5 * 25);
		setBoatHeight(1 * 25);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		Coordinate[] coords = getBoatPositions(getPosition());
		
		for (int i = 0; i < 5; i++){
			rect = new Rectangle2D.Double(coords[i].getX() * getSquareSize(), coords[i].getY() * getSquareSize(), getSquareSize(), getSquareSize());
			g.setColor(Color.yellow);
			g2d.fill(rect);	
		}
		
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[5];
		switch (pos){
		case 0:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,0);
			coords[2] = new Coordinate(2,0);
			coords[3] = new Coordinate(3,0);
			coords[4] = new Coordinate(4,0);
			break;
		case 1:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(0,1);
			coords[2] = new Coordinate(0,2);
			coords[3] = new Coordinate(0,3);
			coords[4] = new Coordinate(0,4);
			break;
		}
		
		return coords;
	}

}
