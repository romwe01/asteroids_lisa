package main;

import graphics.GraphicsConfig;
import core.Core;

public class AsteroidsGame {

	
	final static boolean FULLSCREEN = false;
	static Core c;
	static int defaultWidth = 900;
	static int defaultHeight = 600;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		c = new Core(new GraphicsConfig(defaultWidth, defaultHeight, 60, FULLSCREEN));
		
		
		while (c.getRunLoop() != false){
			c.requestUpdate();
		}
		
		c.closeWindow();
		
		
	}

}
