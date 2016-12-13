package view;

import javax.swing.*;

import model.AtackType;
import model.WeaponType;

import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {

	private int squareSize = 25;
	private int numColumns = 15;
	private int numLines = 15;
	private Color[][] colorMatrix = new Color[15][15];
	
	public GameBoard() {
		this.setLayout(null);
		
		colorMatrix = new Color[15][15];
		for (int i = 0; i < this.getNumLines(); i++){
			for (int j = 0 ; j < this.getNumColumns(); j++ ){
				colorMatrix[i][j] = Color.cyan;
			}
		}
		this.setDoubleBuffered(true);
		
		// coordinates line labels (A - B - C - D ...)
		for (int i = 1 ; i <= 15; i++ ){
			JPanel coordLine = new JPanel();
			Label lineLabel = new Label("" + (char)(i+'A'-1));
			coordLine.setSize(squareSize, squareSize);
			coordLine.setLocation(0, i * squareSize);
			coordLine.add(lineLabel);
			this.add(coordLine);
		}

		// coordinates column labels (1 - 2 - 3 - ... - 15)
		for (int j = 1 ; j <= 15; j++ ){
			JPanel coordColumn = new JPanel();
			Label lineLabel = new Label("" + j);
			coordColumn.setSize(squareSize, squareSize);
			coordColumn.setLocation(j * squareSize, 0);
			coordColumn.add(lineLabel);
			this.add(coordColumn);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//this.removeAll();
		//this.revalidate();
		
		//g.clearRect(0,0,getWidth(),getHeight());
		
		Graphics2D g2d=(Graphics2D) g;
		int line, column;
		
		// board matrix
		for (line=1; line <= numLines; line++){
			for (column=1; column <= numColumns; column++){
				Rectangle2D rect = new Rectangle2D.Double(squareSize*column,squareSize*line, squareSize, squareSize);
				g.setColor(colorMatrix[column-1][line-1]);
				g2d.fill(rect);
				g.setColor(Color.black);
				g2d.draw(rect);
			}
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
	
	public void updatePlayerBoard(WeaponType[][] weaponMatrix) {
		for (int i = 0; i < this.getNumLines(); i++){
			for (int j = 0 ; j < this.getNumColumns(); j++ ){
				colorMatrix[i][j] = Color.cyan;
			}
		}
		
		for (int i = 0; i < this.getNumLines(); i++){
			for (int j = 0 ; j < this.getNumColumns(); j++ ){
				Color color;
				WeaponType type = weaponMatrix[i][j];
				if(type != null) {
					switch (type) {

					case couracado: color = Color.pink; break;
					case cruzador: color = Color.orange; break;
					case destroyer: color = Color.magenta; break;
					case submarino: color = Color.gray; break;
					case hidroaviao: color = Color.green; break;

					default: color = Color.cyan; break;
					}
					colorMatrix[i][j] = color;
				}
			}
		}
		
		this.repaint();
	}
	
	public void updateAtackBoard(AtackType[][] atackMatrix){

		for (int i = 0; i < this.getNumLines(); i++){
			for (int j = 0 ; j < this.getNumColumns(); j++ ){
				colorMatrix[i][j] = Color.cyan;
			}
		}
		
		for (int i = 0; i < this.getNumLines(); i++) {
			for (int j = 0 ; j < this.getNumColumns(); j++) {
				if(atackMatrix[i][j] == AtackType.empty) {
					colorMatrix[i][j] = Color.cyan;
				}
				if(atackMatrix[i][j] == AtackType.hit) {
					colorMatrix[i][j] = Color.red;
				}
				if(atackMatrix[i][j] == AtackType.water) {
					colorMatrix[i][j] = Color.blue;
				}
			}
		}
		this.repaint();
	}
	
	
	public Color[][] getColorMatrix() {
		return colorMatrix;
	}
	
}