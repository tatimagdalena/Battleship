package model.weapons;

import model.Coordinate;
import model.Weapon;
import model.WeaponType;

public class Destroyer extends Weapon  {
		
	public Destroyer(int tag){
		super(tag, WeaponType.destroyer);
		
		setNumPositions(2);
		setNumSquares(2);
		setMatricialWidth(2);
		setMatricialHeight(1);
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[2];
		switch (pos){
		case 0:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,0);
			break;
		case 1:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(0,-1);
			break;
		}
		
		return coords;
	}
	

}
