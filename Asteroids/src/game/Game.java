package game;

import core.Core;
import entities.Ship;
import graphics.GraphicsConfig;

public class Game {

	
	static Core core;
	final static boolean FULLSCREEN = false;
	static int defaultWidth = 900;
	static int defaultHeight = 600;
	public static Ship player;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(
				"Press 'x' to go through menu.\n\n"
				+ "During the game: \n"
				+ "Press 'm' to go back to Menu.\n"
				+ "Press 'q' to quit.\n"
				);
		
		core = new Core(new GraphicsConfig(defaultWidth, defaultHeight, 60, FULLSCREEN));
		
		
		while (core.isRunLoop() != false) {
			core.requestUpdate();
			
		}

		core.closeWindow();
		
	}

}
