package model.weapons;

import model.Weapon;
import utils.Coordinate;

public class Submarino extends Weapon {
		
	public Submarino(int tag){
		super(tag);
		setNumPositions(1);
		setNumSquares(1);
		setMatricialWidth(1);
		setMatricialHeight(1);
	}

	@Override
	public Coordinate[] getBoatPositions(int pos) {
		Coordinate[] coords = new Coordinate[1];
		coords[0] = new Coordinate(0,0);
		return coords;
	}

}
