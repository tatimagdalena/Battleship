package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {

	private int squareSize = 25;
	private int numColumns = 15;
	private int numLines = 15;
	//private Player player;
	private Color[][] matrix = new Color[15][15];
	
	public GameBoard() {
		matrix = new Color[15][15];
		for (int i = 0; i < this.getNumLines(); i++){
			for (int j = 0 ; j < this.getNumColumns(); j++ ){
				matrix[i][j] = Color.cyan;
			}
		}
		this.setDoubleBuffered(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		int line, column;
		
		setLayout(null);
		
		for (int i = 1 ; i <= this.getNumLines(); i++ ){
			JPanel coordLine = new JPanel();
			Label lineLabel = new Label("" + (char)(i+'A'-1));
			coordLine.setSize(squareSize, squareSize);
			coordLine.setLocation(0, i * squareSize);
			coordLine.add(lineLabel);
			this.add(coordLine);
		}
		
		for (line=1; line <= numLines; line++){
			for (column=1; column <= numColumns; column++){
				Rectangle2D rect = new Rectangle2D.Double(squareSize*column,squareSize*line, squareSize, squareSize);
				g.setColor(matrix[column-1][line-1]);
				g2d.fill(rect);
				g.setColor(Color.black);
				g2d.draw(rect);
			}
		}
		
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
	
	public JPanel associatedColumnCoord() {
		JPanel coordColumn = new JPanel();
		coordColumn.setLayout(new GridLayout(1, numColumns));
		coordColumn.setSize(getSquareSize() * getNumColumns(), getSquareSize());
		coordColumn.setLocation((int)getLocation().getX(), (int)getLocation().getY() - getSquareSize());
		
		for (int j = 0 ; j < getNumLines(); j++) {
			Label columnLabel = new Label("" + (char)(j+'A'));
			columnLabel.setAlignment(Label.CENTER);
			coordColumn.add(columnLabel);
		}
		return coordColumn;
	}
	
	public JPanel associatedLineCoord() {
		JPanel coordLine = new JPanel();
		coordLine.setLayout(new GridLayout(numLines, 1));
		coordLine.setSize(getSquareSize(), getSquareSize() * getNumColumns());
		coordLine.setLocation((int)getLocation().getX() - getSquareSize(), (int)getLocation().getY());
		
		for (int i = 1 ; i <= getNumLines(); i++) {
			Label lineLabel = new Label("" + i);
			lineLabel.setAlignment(Label.CENTER);
			coordLine.add(lineLabel);
		}
		return coordLine;
	}
	
	public void setCoordColor(int i, int j, Color color){
		matrix[i-1][j-1] = color;		
	}
	
	
	public Color[][] getMatrix() {
		return matrix;
	}
	
}