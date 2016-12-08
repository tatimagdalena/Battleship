package model;

public class Weapon {
	
	private WeaponType type = WeaponType.generico;
	
	private int numPositions = 2;	//Number of positions a boat has. Hidroavaio: 4, submarino: 1, others: 2
	private int position = 0;   	//Current position a Boat is in
	private int numSquares;			//Number of squares that draw a Boat
	private int tag;				//THe tag to identify the Boat
	private int width;				//Matrix width
	private int height;				//Matrix height
	
	private Boolean sunk = false;
	private int numHitSquares = 0;  //Number of squares that has already been hit by opponent
	
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
		
		int temp = getMatricialHeight();
		setMatricialHeight(getMatricialWidth());
		setMatricialWidth(temp);
		
		setPosition(position);
	}
	
	public void setMatricialWidth(int num){
		width = num;
	}
	
	public int getMatricialWidth(){
		return width;
	}
	
	public void setMatricialHeight(int num){
		height = num;
	}
	
	public int getMatricialHeight(){
		return height;
	}
	
	public Boolean isSunk() {
		return sunk;
	}
	
	public void setSunk(Boolean sunkStatus) {
		sunk = sunkStatus;
	}
	
	public int getNumHitSquares() {
		return numHitSquares;
	}
	
	public void incrementHitSquares() {
		numHitSquares++;
	}
}
