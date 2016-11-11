import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EX1001 {
	public static void main(String[] args) {
		WindowFrame f = new WindowFrame(); 
		f.setTitle("Batalha Naval"); 
		f.setVisible(true);
		
		f.getPanel().addMouseListener(new MouseAdapter() {// provides empty implementation of all
            // MouseListener`s methods, allowing us to
            // override only those which interests us
			@Override //I override only one method for presentation
			public void mousePressed(MouseEvent e) {
			System.out.println(e.getX() + "," + e.getY());
			int line = (e.getX() - 500)/25;
			int column = (e.getY() - 100)/25;
			System.out.println(line + "," + column);
			}
		});

	} 
	
	
}