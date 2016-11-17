import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Boat extends JPanel {
	private int squareSize = 25;
	
	private int tag;
	
	public Boat(int tag){
		this.tag = tag;
	}
	
	public int getSquareSize() {
		return squareSize;
	}
	
	public void setTag(int number){
		tag = number;
	}
	
	public int getTag(){
		return this.tag;
	}
}
