package main;

import core.Core;
import entities.Asteroid;
import entities.Entity;
import entities.EntityManager;
import entities.Ship;
import graphics.GraphicsConfig;
import core.Core;

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

		c = new Core(new GraphicsConfig(defaultWidth, defaultHeight, 60, FULLSCREEN));
		
		//Entity player
		player = new Ship("Player 1", c, 50, 50, 50);
		
		//Entity asteroids
		c.addEntity(new Asteroid("astroid 1", c, -100, 100));
		//add to entityManager
		c.addEntity(player);
		
		
		while (c.getRunLoop() != false){
			c.requestUpdate();
			
		}
		
		c.closeWindow();
		
		
	}

}
