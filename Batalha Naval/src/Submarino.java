import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Submarino extends Boat implements Shape {
	private int width = getSquareSize();
	private int height = getSquareSize();
	
	private Rectangle2D rect;
		
	Submarino(int tag){
		super(tag);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		rect = new Rectangle2D.Double(0,0, getSquareSize(), getSquareSize());
				
		g.setColor(Color.blue);
		g2d.fill(rect);	
	}
	
	@Override
	public int getBoatWidth() {
		return width;
	}

	@Override
	public int getBoatHeight() {
		return height;
	}

}
