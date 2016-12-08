package model.weapons;

import model.Coordinate;
import model.Weapon;
import model.WeaponType;

public class Couracado extends Weapon {
	
	public Couracado(int tag){
		super(tag, WeaponType.couracado);
		
		setNumPositions(2);
		setNumSquares(5);
		setMatricialWidth(5);
		setMatricialHeight(1);
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[5];
		switch (pos){
		case 0:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,0);
			coords[2] = new Coordinate(2,0);
			coords[3] = new Coordinate(3,0);
			coords[4] = new Coordinate(4,0);
			break;
		case 1:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(0,-1);
			coords[2] = new Coordinate(0,-2);
			coords[3] = new Coordinate(0,-3);
			coords[4] = new Coordinate(0,-4);
			break;
		}
		
		return coords;
	}

}
