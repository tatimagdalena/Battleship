package view;

import javax.swing.*;

import model.Coordinate;
import model.Player;
import model.Weapon;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {

	private int squareSize = 25;
	private int numColumns = 15;
	private int numLines = 15;
	private Color[][] matrix = new Color[15][15];
	
	public GameBoard() {
		matrix = new Color[15][15];
		for (int i = 0; i < this.getNumLines(); i++){
			for (int j = 0 ; j < this.getNumColumns(); j++ ){
				matrix[i][j] = Color.cyan;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		int line, column;
		
		setLayout(null);
		
		// coordinates line labels (A - B - C - D ...)
		for (int i = 1 ; i <= this.getNumLines(); i++ ){
			JPanel coordLine = new JPanel();
			Label lineLabel = new Label("" + (char)(i+'A'-1));
			coordLine.setSize(squareSize, squareSize);
			coordLine.setLocation(0, i * squareSize);
			coordLine.add(lineLabel);
			this.add(coordLine);
		}
		
		// board matrix
		for (line=1; line <= numLines; line++){
			for (column=1; column <= numColumns; column++){
				Rectangle2D rect = new Rectangle2D.Double(squareSize*column,squareSize*line, squareSize, squareSize);
				g.setColor(matrix[column-1][line-1]);
				g2d.fill(rect);
				g.setColor(Color.black);
				g2d.draw(rect);
			}
		}
		
		// coordinates column labels (1 - 2 - 3 - ... - 15)
		for (int j = 1 ; j <= this.getNumLines(); j++ ){
			JPanel coordColumn = new JPanel();
			Label lineLabel = new Label("" + j);
			coordColumn.setSize(squareSize, squareSize);
			coordColumn.setLocation(j * squareSize, 0);
			coordColumn.add(lineLabel);
			this.add(coordColumn);
		}
	}
	
	public int getSquareSize(){
		return squareSize;
	}
	
	public int getNumLines(){
		return numLines;
	}
	
	public int getNumColumns(){
		return numColumns;
	}
	
	public void setCoordColor(int i, int j, Color color){
		matrix[i-1][j-1] = color;		
	}
	
	public void updateBoardForPlayer(Player player){
		for (int i = 0; i < this.getNumLines(); i++){
			for (int j = 0 ; j < this.getNumColumns(); j++ ){
				matrix[i][j] = Color.cyan;
			}
		}
		
		for (int i = 0; i < player.getWeapons().length; i++){
			if(player.getWeapons()[i] != null){
				Weapon weapon = player.getWeapons()[i];
				int tag = weapon.getTag();
				Color color;
				if (tag >= 0 && tag < 5){
					color = Color.green;
				} else if (tag > 4 && tag < 8){
					color = Color.magenta;
				} else if (tag > 7 && tag < 12){
					color = Color.blue;
				} else if (tag > 11 && tag < 14){
					color = Color.orange;
				} else {
					color = Color.pink;
				}
				
				Coordinate[] coords = weapon.getBoatPositions(weapon.getPosition());
				for(int j = 0; j < coords.length; j++){
					setCoordColor(weapon.getInitialCoordinate().getX() + coords[j].getX(), 
							weapon.getInitialCoordinate().getY() - coords[j].getY(), 
							color);
				}
			}
		}
		this.repaint();
	}
	
	
	//TODO: move the checking for a controller, passing here only the coordinates and it's colors.
	public void updateAtackBoardForPlayer(Player player, Player opponent){
		for (int i = 0; i < this.getNumLines(); i++){
			for (int j = 0 ; j < this.getNumColumns(); j++ ){
				matrix[i][j] = Color.cyan;
			}
		}
		
		ArrayList<Coordinate> atacks = player.getAtacks();
		
		for(Coordinate atack: atacks) {
			Weapon hitWeapon = opponent.getHitWeapon(atack);
			if(hitWeapon != null) {
				setCoordColor(atack.getX(), atack.getY(), Color.red);
				//System.out.printf("\nAtingiu um %s\n", hitWeapon.getWeaponType().name());
			}
			else {
				setCoordColor(atack.getX(), atack.getY(), Color.blue);
				//System.out.printf("\nAgua!\n");
			}
		}
		
		this.repaint();
	}
	
	
	public Color[][] getMatrix() {
		return matrix;
	}
	
}