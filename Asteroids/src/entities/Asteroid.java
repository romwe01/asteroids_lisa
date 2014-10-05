package entities;

import java.awt.Graphics2D;

import statemachine.PlayState;
import vector.Vector2D;
import core.Core;
import collider.CircleCollider;
import emmitter.EmmitterSettings;

public class Asteroid extends Entity {

	private static final double PI = Math.PI;
	private static int radius = 50;
	private boolean alive = true;
	
	private float screenXLeft, screenXRight, screenYUp, screenYDown;
	private static float x;
	private static float y;
	private float driftDir;

	public Asteroid(String name, PlayState state,Core core, EmmitterSettings es) {
		super(name,state, core, x, y, radius);
		
		//shape
		this.vertices.add(new Vector2D(2, 0));
		this.vertices.add(new Vector2D(2, -1));
		this.vertices.add(new Vector2D(1, -2));
		this.vertices.add(new Vector2D(-2, -2));
		this.vertices.add(new Vector2D(-1.5, -1.5));
		this.vertices.add(new Vector2D(-2, -1));
		this.vertices.add(new Vector2D(-2, 0.5));
		this.vertices.add(new Vector2D(-1.5, 1));
		this.vertices.add(new Vector2D(-1.5, 2));
		this.vertices.add(new Vector2D(-0.5, 2));
		this.vertices.add(new Vector2D(0, 1.5));
		this.vertices.add(new Vector2D(1, 1.5));
		
		
		this.angle = (float) (Math.random()*(Math.PI*2));
		this.scale = new Vector2D(10,10);
		this.velocity = new Vector2D(40,40);
		setUpScreenEdge();
		setUpPosition();
		
		
		state.collisionT.addCollisionObject(getCollisionObject());
	}

	private void setUpScreenEdge() {
		screenXRight = core.graphicsConfig.getWidth()/2;
		screenXLeft = screenXRight *-1;
		screenYUp = core.graphicsConfig.getHeight()/2;
		screenYDown = screenYUp *-1;
	}

	/**
	 * Sets the position of the emmitting point on the edge of the screen.
	 */
	private void setUpPosition() {
		int scrWidth = core.graphicsConfig.getWidth();
		int scrHeight = core.graphicsConfig.getHeight();
		int side = (int)(Math.random()*4);
		

		switch(side){
		//left edge
		case 0:
			//System.out.println("Asteroid von links");
			x = screenXLeft;
			y = (float) ((Math.random()* scrHeight) - scrHeight/2);
			driftDir = (float) ((Math.random()*PI) - (PI/2));
			break;
		//lower edge
		case 1:
//			System.out.println("Asteroid von unten");
			x = (float) ((Math.random()*scrWidth) - scrWidth/2);
			y = -(scrHeight/2);
			driftDir = (float) ((Math.random()*PI));
			break;
		//right edge	
		case 2:
//			System.out.println("Asteroid von rechts");
			x = scrWidth/2;
			y = (float) ((Math.random()*scrHeight) - scrHeight/2);
			driftDir = (float) ((Math.random()*PI) + (PI/2));
			break;
		//upper edge
		case 3:
//			System.out.println("Asteroid von oben");
			x = (float) ((Math.random()*scrWidth) - scrWidth/2);
			y = scrHeight/2;
			driftDir = (float) ((Math.random()*PI) + PI);
			break;
		}
	}

	/**
	 * Uses own update function for better drifting.
	 */
	public void update(){
		if (isAlive() && alive){
			this.position.setY( (float) (this.position.getY() + this.velocity.getY() * Math.sin(driftDir) * core.getDT()));
			this.position.setX( (float) (this.position.getX() + this.velocity.getX() * Math.cos(driftDir) * core.getDT()));
			angle = (float) ((angle + 0.09f) %360);
			this.transform();
			super.updateCollider((float)super.getPosition().getX(), (float)super.getPosition().getY());
		}
		else{
			//System.out.println("Asteroid zerstört");
			state.collisionT.removeCollisionObj(getCollisionObject());
			state.entityManager.removeEntity(this);
		}
	}
	
	/**
	 * Checks if the asteroid is off the screen.
	 * @return returns false if the asteroid is off the screen
	 */
	private boolean isAlive() {
		return !(x < (screenXLeft - radius) || x > (screenXRight + radius) || y < (screenYDown - radius)
				|| y > (screenYUp + radius));
	}



	public void render(Graphics2D g){
		super.render(g);
	}
	
	@Override
	public void collided(CircleCollider col) {
		alive = false;
		
	}

}
