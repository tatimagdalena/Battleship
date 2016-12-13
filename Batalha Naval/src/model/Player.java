package model;

import java.util.ArrayList;
import java.util.Observable;

public class Player extends Observable{

	final PlayerTurn turn;
	final String name;
	
	private Weapon[] weapons = new Weapon[15]; // total of 15 weapons
	private ArrayList<Coordinate> atacks = new ArrayList<Coordinate>();
	
	private int shots = 3;
	
	/**
	 * inits
	 * @param name name to be displayed
	 * @param turn enum for first, second as order of plays
	 */
	public Player(String name, PlayerTurn turn) {
		this.turn = turn;
		this.name = name;
	}
	
	/**
	 * Getter
	 * @return string for the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter
	 * @return enum for turn of the player, first or second
	 */
	public PlayerTurn getTurn() {
		return turn;
	}
	
	/**
	 * Getter
	 * @return int number of shots left
	 */
	public int getShotsLeft() {
		return shots;
	}
	
	/**
	 * Refresh player total of shots to 3, the maximum
	 */
	public void refreshShots() {
		shots = 3;
	}
	
	/**
	 * Setter the number of shots to equal num
	 * @param num int absolute number of shots
	 */
	public void setShots(int num) {
		shots = num;
	}
	
	/**
	 * Inserts Boat on weapon array
	 * @param weapon reference to the weapon to be inserted
	 */
	public void addWeapon(Weapon weapon) {
		weapons[weapon.getTag()] = weapon;
	}
	
	/**
	 * Removes Boat from the weapon array
	 * @param weapon reference to boat to be removed
	 */
	public void removeWeapon(Weapon weapon) {
		weapons[weapon.getTag()] = null;
	}
	
	/**
	 * Getter
	 * @return weapons array
	 */
	public Weapon[] getWeapons() {
		return weapons;
	}
	
	/**
	 * Getter
	 * @return array with all attacks made by the player
	 */
	public ArrayList<Coordinate> getAtacks() {
		return atacks;
	}
	
	/**
	 * Realize an attack on the other player.
	 * Only works if the attack has not already been made
	 * If works realized the shot and discount from shot count
	 * @param atack matricial coordinate where attack will be made
	 */
	public void setNewAtack(Coordinate atack) {
		if (existsAttack(atack.getX(), atack.getY())) {
			return;
		}
		atacks.add(atack);
		shots--;
		this.setChanged();
		this.notifyObservers(atack);
	}
	
	public void appendToAtacks(Coordinate atack) {
		atacks.add(atack);
	}
	
	/**
	 * Check for the coordinate where the player wants to place the weapon if it can be inserted.
	 * The boat cannot be placed right next to other boat or on the direct diagonal. 
	 * The distance must be of at least 1 square in every direction.
	 * @param weapon weapon to be inserted
	 * @param position weapon position
	 * @return true if possible, false if not
	 */
	public Boolean checkValidPosition(Weapon weapon, Coordinate position) {
		
		Coordinate[] coordinates = weapon.getBoatPositions(weapon.getPosition());
		for(Coordinate coord: coordinates) {
			int relativeX = position.getX() + coord.getX();
			int relativeY = position.getY() - coord.getY();
						
			if(relativeX < 1 || relativeX > 15 || relativeY < 1 || relativeY > 15){
				return false;
			}
			//Check if exists boat on coordinate
			if(	existsWeapon(relativeX, relativeY) ||		//Position
				existsWeapon(relativeX-1, relativeY) || 	//to the left
				existsWeapon(relativeX+1, relativeY) || 	//to the right
				existsWeapon(relativeX, relativeY+1) || 	//south
				existsWeapon(relativeX, relativeY-1) || 	//north
				existsWeapon(relativeX+1, relativeY+1) || 	//south right
				existsWeapon(relativeX+1, relativeY-1) || 	//north right
				existsWeapon(relativeX-1, relativeY+1) || 	//south left
				existsWeapon(relativeX-1, relativeY-1)    	//north right
					){
				return false;
			}	
		}
		return true;
	}
	
	/**
	 * Check if exists a weapon in the given place
	 * @param i matricial x coordinate
	 * @param j matricial y coordinate
	 * @return true if exists, false if not
	 */
	public Boolean existsWeapon(int i, int j) {
		Coordinate selectedCoord = new Coordinate(i, j);
		for(Weapon weapon: weapons) {
			if(weapon != null) {
				Coordinate[] coordinates = weapon.getBoatPositions(weapon.getPosition());
				for(Coordinate coord: coordinates) {
					int relativeX = weapon.getInitialCoordinate().getX() + coord.getX();
					int relativeY = weapon.getInitialCoordinate().getY() - coord.getY();
					Coordinate relativeCoord = new Coordinate(relativeX, relativeY);
					if(relativeCoord.equals(selectedCoord)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if an attack had already been made in that position
	 * @param i matricial x coordinate
	 * @param j matricial y coordinate
	 * @return true if it has, false if not
	 */
	private Boolean existsAttack(int i, int j) {
		Coordinate selectedCoord = new Coordinate(i, j);
		
		ArrayList<Coordinate> coordinates = this.getAtacks();
		for(Coordinate coord: coordinates) {
			int relativeX = coord.getX();
			int relativeY = coord.getY();
			Coordinate relativeCoord = new Coordinate(relativeX, relativeY);
			if(relativeCoord.equals(selectedCoord)) {
				return true;
			}
		}
		return false;
	}
		
	/**
	 * Getter for the weapon on the coordinate
	 * @param selectedCoord coord where it must check
	 * @return the weapon, if it was a hit, or null if hit the water
	 */
	public Weapon getHitWeapon(Coordinate selectedCoord) {
		for(Weapon weapon: weapons) {
			if(weapon != null) {
				Coordinate[] coordinates = weapon.getBoatPositions(weapon.getPosition());
				for(Coordinate coord: coordinates) {
					int relativeX = weapon.getInitialCoordinate().getX() + coord.getX();
					int relativeY = weapon.getInitialCoordinate().getY() - coord.getY();
					Coordinate relativeCoord = new Coordinate(relativeX, relativeY);
					if(relativeCoord.equals(selectedCoord)) {
						return weapon;
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String s;
		
		s = turn.name()
			+ "\n"
			+ name
			+ "\n"
			+ "restam:"
			+ "\n"
			+ shots
			+ "\n"
			+ "ARMAS:";
		
		for(Weapon weapon: weapons) {
			
			if(weapon != null) {
				s = s
					+ "\n"
					+ weapon.toString();
			}
			else {
				s = s
					+ "\n"
					+ "null";
			}
		}
				
		s = s
			+ "\n"
			+ "ATAQUES:"
			+ "\n"
			+ atacks.size()
			+ " ";
			
			
		for(Coordinate atack: atacks) {
			s = s
				+ atack.toString()
				+ " ";
		}
		
		s = s + "\n";
		
		return s;
	}
}
