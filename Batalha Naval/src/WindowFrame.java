import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {
	
	public final int LARG_DEFAULT=800; 
	public final int ALT_DEFAULT=600;
	
	
	public WindowFrame() {
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		
		getContentPane().add(new GameBoard());
	
	}
	
}