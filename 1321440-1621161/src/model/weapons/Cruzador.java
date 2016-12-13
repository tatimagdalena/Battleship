package model.weapons;

import model.Coordinate;
import model.Weapon;
import model.WeaponType;

public class Cruzador extends Weapon {
		
	public Cruzador(int tag){
		super(tag, WeaponType.cruzador);
		
		setNumPositions(2);
		setNumSquares(4);
		setMatricialWidth(4);
		setMatricialHeight(1);
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[4];
		switch (pos){
		case 0:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(1,0);
			coords[2] = new Coordinate(2,0);
			coords[3] = new Coordinate(3,0);
			break;
		case 1:
			coords[0] = new Coordinate(0,0);
			coords[1] = new Coordinate(0,-1);
			coords[2] = new Coordinate(0,-2);
			coords[3] = new Coordinate(0,-3);
			break;
		}
		
		return coords;
	}

}
