import java.awt.*;

public interface Shape {
	
	int getBoatWidth();
	int getBoatHeight();
	Color getBoatColor();
	void setBoatWidth(int width);
	void setBoatHeight(int height);
	void setBoatColor(Color color);
	int getSquareSize();
	int getNumPositions();
	public Coordinate[] getBoatPositions(int pos);
	
}
