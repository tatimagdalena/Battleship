import javax.swing.*; 
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;


public class GameBoard extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawString("Vez do jogador 1, arraste os navios para o tabuleiro de jogo", 10, 10);
		
		int line, column;
		
		for (line=0; line<16; line++){
			for (column=0; column<16; column++){
				Graphics2D g2d=(Graphics2D) g;
				Rectangle2D rect = new Rectangle2D.Double( 500 + 25*column,  100 + 25*line, 25, 25);
				//g2d.setPaint(Color.WHITE);
				g2d.draw(rect);
			}
		}

	} 
	
}