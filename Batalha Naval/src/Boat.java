import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Boat extends JPanel implements Shape {
	private int squareSize = 25;
	private int numPositions = 2;
	private int position = 0;
	private int numSquares;
	private int width;
	private int height;
	private int tag;
	
	public Boat(int tag){
		this.tag = tag;
	}
	
	public int getSquareSize() {
		return squareSize;
	}
	
	public void setTag(int number) {
		tag = number;
	}
	
	public int getTag(){
		return this.tag;
	}
	
	public int getNumPositions() {
		return numPositions;
	}
	
	public void setNumPositions(int num) {
		numPositions = num;
	}
	
	public int getNumSquares() {
		return numSquares;
	}
	
	public void setNumSquares(int num) {
		numSquares = num;
	}

	@Override
	public int getBoatWidth() {
		return width;
	}

	@Override
	public int getBoatHeight() {
		return height;
	}
	
	@Override
	public void setBoatWidth(int width) {
		this.width = width;
	}
	
	@Override
	public void setBoatHeight(int height) {
		this.height = height;
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		return null;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
	
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
	
}
