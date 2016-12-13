package model;

public class Weapon {
	
	private WeaponType type = WeaponType.generico;
	
	private int tag;				//THe tag to identify the Boat
	private int width;				//Matrix width
	private int height;				//Matrix height
	private int numPositions = 2;	//Number of positions a boat has. Hidroavaio: 4, submarino: 1, others: 2
	private int position = 0;   	//Current position a Boat is in
	private int numSquares;			//Number of squares that draw a Boat
	private int numHitSquares = 0;  //Number of squares that has already been hit by opponent
	
	private Boolean sunk = false;
	
	private Coordinate initialCoordinate = new Coordinate(-1,-1); //The initial coordinate of this weapon when positioned on the game board.
	
	public Weapon(int tag, WeaponType type){
		this.tag = tag;
		this.type = type;
	}
	
	public WeaponType getWeaponType() {
		return type;
	}
	
	public void setInitialCoordinate(Coordinate coord) {
		initialCoordinate = coord;
	}
	
	public Coordinate getInitialCoordinate() {
		return initialCoordinate;
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

	/**
	 * Description: This function must always be overwritten.
	 * @param pos the position the boat is in
	 * @return: An array with the coordinate of each square relative to the mouse arrow
	 * 
	 */
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
	public void setPosition(int position){
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
		
		int temp = getMatricialHeight();
		setMatricialHeight(getMatricialWidth());
		setMatricialWidth(temp);
		
		setPosition(position);
	}
	
	/**
	 * Setter for the matricial width for the boat
	 * @param num how many squares it ocupies on the horizontal
	 */
	public void setMatricialWidth(int num){
		width = num;
	}
	
	/**
	 * Getter for the matricial width for the boat
	 * @return num how many squares it ocupies on the horizontal
	 */
	public int getMatricialWidth(){
		return width;
	}
	
	/**
	 * Setter for the matricial height for the boat
	 * @param num how many squares it ocupies on the vertical
	 */
	public void setMatricialHeight(int num){
		height = num;
	}
	
	/**
	 * Getter for the matricial height for the boat
	 * @return num how many squares it ocupies on the vertical
	 */
	public int getMatricialHeight(){
		return height;
	}
	
	/**
	 * Check if the boat is sunk
	 * @return true if it's sunk, false if it's alive
	 */
	public Boolean isSunk() {
		return sunk;
	}
	
	/**
	 * Set Boat sunk status
	 * @param sunkStatus true if it's sunk, false if it's alive
	 */
	public void setSunk(Boolean sunkStatus) {
		sunk = sunkStatus;
	}
	
	/**
	 * Getter for how many squares were hit
	 * @return int number of hits taken
	 */
	public int getNumHitSquares() {
		return numHitSquares;
	}
	
	/**
	 * Increments number of hits taken
	 */
	public void incrementHitSquares() {
		numHitSquares++;
	}
	
	public void setNumHitSquares(int n) {
		numHitSquares = n;
	}
	
	@Override
	public String toString() {
		String s;
		
		s = 	type.name()
				+ " "
				+ tag
				+ " "
//				+ width
//				+ " "
//				+ height
//				+ " "
//				+ numSquares
//				+ " "
				+ numHitSquares
				+ " "
				+ position
				+ "\n"
				+ sunk.toString()
				+ "\n"
//				+ numPositions
//				+ " "
				+ initialCoordinate.toString()
				+ " ";
				
				
//		Coordinate[] absolutePositions = getBoatPositions(position);
//		
//		for(Coordinate coord: absolutePositions) {
//			s = 	s
//					+ coord.toString()
//					+ " ";
//		}
		
		return s;
	}
	
	public Coordinate[] getRelativePositions() {
		
		Coordinate[] absolutePositions = getBoatPositions(position);
		Coordinate[] relativePositions = new Coordinate[absolutePositions.length];
		
		for(int i = 0; i < absolutePositions.length; i++) {
			Coordinate absPos = absolutePositions[i];
			Coordinate relPos = new Coordinate(absPos.getX() + initialCoordinate.getX(), absPos.getY() - initialCoordinate.getY());
			relativePositions[i] = relPos;
		}
		
		return relativePositions;
	}

}
