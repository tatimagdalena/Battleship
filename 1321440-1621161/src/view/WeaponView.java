package view;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import model.Coordinate;
import model.Weapon;

@SuppressWarnings("serial")
public class WeaponView extends JPanel {
	
	private Weapon weapon;
	private Rectangle2D rect;
	private Color color;
	
	WeaponView(Weapon weapon, Color color){
		this.weapon = weapon;
		this.color = color;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		
		Coordinate[] coords = weapon.getBoatPositions(weapon.getPosition());
		
		for (int i = 0; i < weapon.getNumSquares(); i++){
			rect = new Rectangle2D.Double(coords[i].getX() * 25, -coords[i].getY() * 25, 25, 25);
			g.setColor(color);
			g2d.fill(rect);	
		}
		
		//setOpaque(false);
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
	
}
