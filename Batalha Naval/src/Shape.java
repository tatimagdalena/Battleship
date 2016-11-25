import java.awt.Graphics;

public interface Shape {
	
	int getBoatWidth();
	int getBoatHeight();
	void setBoatWidth(int width);
	void setBoatHeight(int height);
	int getSquareSize();
	int getNumPositions();
	public Coordinate[] getBoatPositions(int pos);
	
}
