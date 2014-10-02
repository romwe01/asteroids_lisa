package entities;

import java.awt.Graphics2D;

import collision.CircleCollider;

import core.Core;

public class Asteroid extends Entity{

	private static int radius = 60;
	private boolean alive = true;
	private float speed = 1;
	
	
	public Asteroid(String name, Core core, float d, float e) {
		super(name, core, d, e, radius);
		
		position = new Vector2D(d, e);
		//velocity = new Vector2D(speed, speed);
		// shape
		double num_points = Math.random()*10+3;
		int num_p = (int) num_points;
		for (int i = 0; i < num_p; i++){
			double angle = i*2*Math.PI /num_p;
			Vector2D point = new Vector2D(Math.cos(angle), Math.sin(angle));
			vertices.add(point);
		}
		/*
		vertices.add(new Vector2D(0,0));
		vertices.add(new Vector2D(0,1.5));
		vertices.add(new Vector2D(1.5,2));
		vertices.add(new Vector2D(2,1));
		vertices.add(new Vector2D(1,0));
		*/
		
		scale = new Vector2D(30, 30);
		core.cT.addCollisionObject(getCollisionObject());
	}
	
	/**
	 * calls all methods for transforming the asteroid entity 
	 */
	private void transform() {
		
		this.verticesTrans.clear();
		
		for (Vector2D vt : this.vertices){
			vt = vt.scaled(this.scale);
			vt = vt.rotated(this.angle * Math.PI / 180);
			vt = vt.translated(this.position);
			this.verticesTrans.add(vt);
		}	
	}

	@Override
	/**
	 * updates the new position of the asteroid and collider
	 */
	public void update() {
		
		// Velocity / Geschwindigkeit
		Vector2D vel = new Vector2D(this.velocity.getX() * core.getDT(),this.velocity.getY() * core.getDT());

		this.position.set(this.position.getX() + vel.getX(),this.position.getY() + vel.getY());

		this.transform();
		Vector2D c = getPosition();
		super.updateCollider(new Vector2D((float)c.getX() - radius/2.0f, (float)c.getY() - radius/2.0f));
	}

	@Override
	/**
	 * renders living asteroids
	 */
	public void render(Graphics2D g) {

		if (alive){
			if (!this.verticesTrans.isEmpty()) {
				Vector2D lastVt = this.verticesTrans.get(this.vertices.size() - 1);
				for (Vector2D vt : this.verticesTrans) {
					g.drawLine((int) vt.getX(), (int) vt.getY(),
							(int) lastVt.getX(), (int) lastVt.getY());
					lastVt = vt;
				}
			}
			super.drawCollider(g);
		}
		
	}

	@Override
	public Vector2D getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(float x, float y) {
		this.position.set(x, y);
	}

	@Override
	public void collided(CircleCollider col) {
		alive = false;
		
	}

}
