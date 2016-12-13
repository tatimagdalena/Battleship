package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenDimensions {

	public final double screenCenterX;
	public final double screenCenterY;
	public final double screenWidth;
	public final double screenHeight;
	public final int screenIntWidth;
	public final int screenIntHeight;
	public final Dimension screenSize;
	
	private static ScreenDimensions screenDimensions = null;
	
	private ScreenDimensions() {
		this.screenSize = getScreenSize();
		this.screenWidth = screenSize.getWidth();
		this.screenHeight = screenSize.getHeight();
		this.screenIntWidth = screenSize.width;
		this.screenIntHeight = screenSize.height;
		this.screenCenterX = screenWidth / 2;
		this.screenCenterY = screenHeight / 2;
	}
	
	public static ScreenDimensions getScreenDimensions() {
		if(screenDimensions == null) {
			screenDimensions = new ScreenDimensions();
		}
		return screenDimensions;
	}
	
	private Dimension getScreenSize() {
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = tk.getScreenSize(); 
		return screenSize;
	}
	
}
