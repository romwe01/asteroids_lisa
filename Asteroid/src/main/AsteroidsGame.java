package main;

import core.Core;
import entities.Entity;
import entities.EntityManager;
import entities.Ship;
import enumeration.Enumerations;
import graphics.GraphicsConfig;
import graphics.RenderLayer;
import time.Time;

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
		
		//Entity player
		Ship player = new Ship("Player 1", c, 50, 50, 50);
		
		//Entity asteroids
		
		//add to entityManager
		c.addEntity(player);
		
		
		while (c.getRunLoop() != false){
			c.requestUpdate();
			
		}
		
		c.closeWindow();
		
		
	}

}
