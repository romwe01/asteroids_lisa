package main;

import core.Core;
import entities.Ship;
import graphics.GraphicsConfig;

public class AsteroidsGame {

	final static boolean FULLSCREEN = false;
	static Core c;
	static int defaultWidth = 900;
	static int defaultHeight = 600;
	public static Ship player;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(
				"Press 'x' to go through menu.\n\n"
				+ "During the game: \n"
				+ "Press 'm' to go back to Menu.\n"
				+ "Press 'q' to quit.\n"
				);
		
		c = new Core(new GraphicsConfig(defaultWidth, defaultHeight, 60,
				FULLSCREEN));

		while (c.getRunLoop() != false) {
			c.requestUpdate();
			
		}

		c.closeWindow();

	}

}
