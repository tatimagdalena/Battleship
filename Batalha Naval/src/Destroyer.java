import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Destroyer extends Boat implements Shape {

	private int width = getSquareSize() * 2;
	private int height = getSquareSize();
	
	private Rectangle2D rect1;
	private Rectangle2D rect2;
		
	Destroyer(int tag){
		super(tag);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		rect1 = new Rectangle2D.Double(0,0, getSquareSize(), getSquareSize());
		rect2 = new Rectangle2D.Double(getSquareSize(),0, getSquareSize(), getSquareSize());
				
		g.setColor(Color.orange);
		g2d.fill(rect1);
		g2d.fill(rect2);

		
	}


	@Override
	public int getBoatWidth() {
		// TODO Auto-generated method stub
		return width;
	}


	@Override
	public int getBoatHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	

}
