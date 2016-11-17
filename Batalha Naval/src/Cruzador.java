import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Cruzador extends Boat implements Shape {
	
	private int width = getSquareSize() * 4;
	private int height = getSquareSize();
	
	private Rectangle2D rect;
		
	public Cruzador(int tag){
		super(tag);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		for (int i = 0; i < 4; i++){
			rect = new Rectangle2D.Double(i * getSquareSize(),0, getSquareSize(), getSquareSize());
			g.setColor(Color.red);
			g2d.fill(rect);	
		}
		
	}
	
	@Override
	public int getBoatWidth() {
		return width;
	}

	@Override
	public int getBoatHeight() {
		// TODO Auto-generated method stub
		return height;
	}

}
