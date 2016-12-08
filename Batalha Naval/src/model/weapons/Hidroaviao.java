package model.weapons;

import model.Coordinate;
import model.Weapon;
import model.WeaponType;

public class Hidroaviao extends Weapon {
	
	
	public Hidroaviao(int tag){
		super(tag, WeaponType.hidroaviao);
		
		setNumPositions(4);
		setNumSquares(3);
		setMatricialWidth(3);
		setMatricialHeight(2);
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[3];
		switch (pos){
		case 0:
			coords[0] = new Coordinate(0,-1);
			coords[1] = new Coordinate(1,0);
			coords[2] = new Coordinate(2,-1);
			break;
		case 1:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,-1);
			coords[2] = new Coordinate(0,-2);
			break;
		case 2:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,-1);
			coords[2] = new Coordinate(2,0);
			break;
		case 3:
			coords[0] = new Coordinate(1,0);
			coords[1] = new Coordinate(0,-1);
			coords[2] = new Coordinate(1,-2);
			break;
		}
		
		return coords;
	}

}
