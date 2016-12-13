package model;

public class Coordinate {
	
	private int x;
	private int y;
	
	/**
	 * inits the coordinate
	 * @param x int matricial coordinate
	 * @param y int matricial coordinate
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Setter
	 * @param x int matricial coordinate
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Getter
	 * @return x int matricial coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Setter
	 * @param y int matricial coordinate
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Getter
	 * @return y int matricial coordinate
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Compares with other coordinate
	 * @return true if equals, false if not;
	 */
	@Override
	public boolean equals(Object obj) {
		Coordinate other = (Coordinate)obj;
		return this.x == other.x && this.y == other.y;
	}
	
	@Override
	public String toString() {
		return x + " " + y; 
	}
}
