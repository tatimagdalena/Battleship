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
	
	private int width = 3 * getSquareSize();
	private int height = 2 * getSquareSize();
	
	Hidroaviao(int tag){
		super(tag);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		rect1 = new Rectangle2D.Double(0,getSquareSize(), getSquareSize(), getSquareSize());
		rect2 = new Rectangle2D.Double(getSquareSize(),0, getSquareSize(), getSquareSize());
		rect3 = new Rectangle2D.Double(getSquareSize()*2,getSquareSize(), getSquareSize(), getSquareSize());
				
		g.setColor(Color.green);
		g2d.fill(rect1);
		g2d.fill(rect2);
		g2d.fill(rect3);

		
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
