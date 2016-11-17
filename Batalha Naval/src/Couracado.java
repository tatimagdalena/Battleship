import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Couracado extends Boat implements Shape {
	private int width = getSquareSize() * 5;
	private int height = getSquareSize();
	
	private Rectangle2D rect;
	
	public Couracado(int tag){
		super(tag);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		for (int i = 0; i < 5; i++){
			rect = new Rectangle2D.Double(i * getSquareSize(),0, getSquareSize(), getSquareSize());
			g.setColor(Color.yellow);
			g2d.fill(rect);	
		}
		
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
