package model;

import controller.GameController;
import utils.Coordinate;
import view.GameBoard;

public class Player {

	final PlayerTurn turn;
	final String name;
	
	private Weapon[] weapons = new Weapon[15]; // total of 15 weapons
	
	
	public Player(String name, PlayerTurn turn) {
		this.turn = turn;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public PlayerTurn getTurn() {
		return turn;
	}
	
	public void addWeapon(Weapon weapon) {
		weapons[weapon.getTag()] = weapon;
	}
	
	public void removeWeapon(Weapon weapon) {
		weapons[weapon.getTag()] = null;
	}
	
	public Weapon[] getWeapons() {
		return weapons;
	}
	
	public Boolean checkValidPosition(Weapon weapon, Coordinate position) {
		
		Coordinate[] coordinates = weapon.getBoatPositions(weapon.getPosition());
		for(Coordinate coord: coordinates) {
			int relativeX = position.getX() + coord.getX();
			int relativeY = position.getY() - coord.getY();
			
			System.out.println("col" + relativeX + "line" + relativeY);
			
			if(relativeX < 1 || relativeX > 15 || relativeY < 1 || relativeY > 15){
				return false;
			}
			
			if(		//Check if exists boat on coordinate
					existsWeapon(relativeX, relativeY) || //Position
					existsWeapon(relativeX-1, relativeY) || //to the left
					existsWeapon(relativeX+1, relativeY) || //to the right
					existsWeapon(relativeX, relativeY+1) || //south
					existsWeapon(relativeX, relativeY-1) || //north
					existsWeapon(relativeX+1, relativeY+1) || //south right
					existsWeapon(relativeX+1, relativeY-1) || //north right
					existsWeapon(relativeX-1, relativeY+1) || //south left
					existsWeapon(relativeX-1, relativeY-1)    //north right
					){
				return false;
			}
			
		}
		
		return true;
		
	}
	
	private Boolean existsWeapon(int i, int j){
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
	
//	public void updateWeapon(Weapon weapon) {
//		weapons[weapon.getTag()] = weapon;
//	}
}