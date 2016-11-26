import javax.swing.JPanel;
import java.awt.*;

@SuppressWarnings("serial")
public class Boat extends JPanel implements Shape {
	
	private int squareSize = 25;
	private int numPositions = 2;	//Number of positions a boat has. Hidroavaio: 4, submarino: 1, others: 2
	private int position = 0;   	//Current position a Boat is in
	private int numSquares;			//Number of squares that draw a Boat
	private int width;				//The width of the boat on it's current position
	private int height;				//The height of the boat on it's current position
	private Color color;			//The boat color
	private int tag;				//THe tag to identify the Boat
		
	public Boat(int tag){
		this.tag = tag;
	}
	
	public int getSquareSize() {
		return squareSize;
	}
	
	public int getTag(){
		return this.tag;
	}
	
	public void setTag(int number) {
		tag = number;
	}
	
	/**
	 * the possible number of positions a boat can be in
	 * @return the number of positions the boat has
	 */
	public int getNumPositions() {
		return numPositions;
	}
	
	/**
	 * Reestablish the number of positions
	 * @param num the number of positions the boat can be in
	 */
	public void setNumPositions(int num) {
		numPositions = num;
	}
	
	/**
	 * Varies for each boat
	 * @return number of squares a boat has
	 */
	public int getNumSquares() {
		return numSquares;
	}
	
	/**
	 * Varies for each boat
	 * @param num set the number of squares a boat has
	 */
	public void setNumSquares(int num) {
		numSquares = num;
	}

	@Override
	public int getBoatWidth() {
		return width;
	}
	
	@Override
	public void setBoatWidth(int width) {
		this.width = width;
	}

	@Override
	public int getBoatHeight() {
		return height;
	}
	
	@Override
	public void setBoatHeight(int height) {
		this.height = height;
	}

	/**
	 * Description: This function must always be overwritten.
	 * @param pos the position the boat is in
	 * @return: An array with the coordinate of each square relative to the mouse arrow
	 * 
	 */
	@Override  
	public Coordinate[] getBoatPositions(int pos) {
		return null;
	}
	
	/**
	 * @return the current position of the boat(To be used in getBoatPositions)
	 */
	public int getPosition(){
		return position;
	}
	
	/**
	 * Set the position of the boat
	 * @param position the position the boat will be in. Generaly 0 is horizontal and 1 is vertical.
	 * for hidroplane 0 is basic and each +1 rotates 90 degrees
	 */
	private void setPosition(int position){
		this.position = position;
	}
	
	/**
	 * set position to the next one and changes the Width and height
	 */
	public void nextPosition() {
		position ++;
		if(position == getNumPositions()){
			position = 0;
		}
		
		int temp = getBoatHeight();
		setBoatHeight(getBoatWidth());
		setBoatWidth(temp);
		
		setPosition(position);
	}

	@Override
	public Color getBoatColor() {
		return color;
	}

	@Override
	public void setBoatColor(Color color) {
		this.color = color;		
	}
	
}
