import javax.swing.*; 
import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawString("Vez do jogador 1, arraste os navios para o tabuleiro de jogo", 10, 10);
		
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		double panelInitialX = screen.screenCenterX;
		double panelInitialY = screen.screenCenterY - 15 * 25/2;
		
		int line, column;
		
		for (line=0; line<16; line++){
			for (column=0; column<16; column++){
				Graphics2D g2d=(Graphics2D) g;
				Rectangle2D rect = new Rectangle2D.Double(panelInitialX + 25*column,  panelInitialY + 25*line, 25, 25);
				//g2d.setPaint(Color.WHITE);
				g2d.draw(rect);
			}
		}
	}
	
}