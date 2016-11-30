package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import utils.Coordinate;

@SuppressWarnings("serial")
public class Destroyer extends Boat implements Shape {
	
	private Rectangle2D rect;
		
	public Destroyer(int tag){
		super(tag);
		
		setNumPositions(2);
		setNumSquares(2);
		setBoatWidth(2 * 25);
		setBoatHeight(1 * 25);
		setBoatColor(Color.orange);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		Coordinate[] coords = getBoatPositions(getPosition());
		
		for (int i = 0; i < 2; i++){
			rect = new Rectangle2D.Double(coords[i].getX() * getSquareSize(), coords[i].getY() * getSquareSize(), getSquareSize(), getSquareSize());
			g.setColor(getBoatColor());
			g2d.fill(rect);	
		}
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[2];
		switch (pos){
		case 0:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,0);
			break;
		case 1:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(0,1);
			break;
		}
		
		return coords;
	}
	

}
