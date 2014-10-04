package entities;

import java.awt.Graphics2D;

import collision.CircleCollider;
import core.Core;

public class Ship extends Entity {

	private int radius;
	private Vector2D acceleration = new Vector2D(20, 20);
	//private Vector2D offset = new Vector2D(100, 100);
	private float maxSpeed = 300f;
	private float friction = 0.90f;
	private Shot[] shots = new Shot[5];
	private int nextShot = 0;
	private float cooldown = 0;
	private float maxCooldown = 0.2f;
	

	/**
	 * creates the ship
	 * @param name
	 * @param core 
	 * @param x x-coordinate for collider
	 * @param y y-coordinate for collider
	 * @param radius radius for collider
	 */
	public Ship(String name, Core core, float x, float y, int radius) {
		super(name, core, x, y, radius);

		this.position = new Vector2D(x, y);
		this.radius = radius;

		// shape
		this.vertices.add(new Vector2D(0, 1));
		this.vertices.add(new Vector2D(-1, -1));
		this.vertices.add(new Vector2D(1, -1));
		
		this.scale = new Vector2D(20, 20);
		core.cT.addPlayerObject(getCollisionObject());
	}
	
	public void setMaxShootCooldown(float maxShootCooldown) {
		maxCooldown = maxShootCooldown;
	}

	/**
	 * produces bullets 
	 * @return new bullet, null 
	 */
	public Shot shoot() {
		if(this.cooldown>0){
			return null;
		}
		this.cooldown = maxCooldown;
		/*
		Shot s = shots[nextShot];

		if (s != null) {
			s.getPosition().set(this.position.getX(), this.position.getY());
			s.setRotation(this.angle);
		} else {
		*/
			shots[nextShot] = new Shot("shot" + System.currentTimeMillis(), core, (float)position.getX(),
					(float)position.getY(), angle);
			Shot s = shots[nextShot];
		
		nextShot++;
		nextShot %= shots.length;
		return s;
	}

	/**
	 * updates the position of the entity and its collider
	 * checks if entity is inside the window
	 */
	@Override
	public void update() {
		/*System.out.println("[" + core.graphicsConfig.Width + ", "
				+ core.graphicsConfig.Height + "]");
		System.out.println("Position: " + this.position.toString());
*/
		if (this.position.getY() > core.graphicsConfig.Height / 2) {
			this.position.setY(-core.graphicsConfig.Height / 2);
		} else if (this.position.getY() < -core.graphicsConfig.Height / 2) {
			this.position.setY(core.graphicsConfig.Height / 2);
		}

		if (this.position.getX() > core.graphicsConfig.Width / 2) {
			this.position.setX(-core.graphicsConfig.Width / 2);
		} else if (this.position.getX() < -core.graphicsConfig.Width / 2) {
			this.position.setX(core.graphicsConfig.Width / 2);
		}
		
		// Velocity / Geschwindigkeit
		Vector2D vel = new Vector2D(this.velocity.getX() * core.getDT(),
				this.velocity.getY() * core.getDT());

		this.position.set(this.position.getX() + vel.getX(),
				this.position.getY() + vel.getY());

		// reduce cooldown
		if(this.cooldown>0){
			this.cooldown-=core.getDT();
		}

		this.transform();
		Vector2D c = getPosition();
		super.updateCollider(new Vector2D((float)c.getX() - radius/2.0f, (float)c.getY() - radius/2.0f));
	}

	/**
	 * calls all methods for transforming the player entity 
	 */
	private void transform() {

		this.verticesTrans.clear();

		for (Vector2D vt : this.vertices) {
			vt = vt.scaled(this.scale);
			vt = vt.rotated(this.angle * Math.PI / 180);
			vt = vt.translated(this.position);
			this.verticesTrans.add(vt);
		}

	}

	/**
	 * in-/decreases the angle of direction
	 * @param clockwise depends on pressed key for in-/decreasing of angle
	 */
	public void rotate(boolean clockwise) {
		if (clockwise) {
			this.angle += 10;
		} else {
			this.angle -= 10;
		}
	}

	/**
	 * increases the speed of the player entity until it reaches maximum speed limit
	 */
	public void accelerate() {
		if (this.velocity.getX() < maxSpeed && this.velocity.getX() > -maxSpeed
				&& this.velocity.getY() < maxSpeed && this.velocity.getY() > -maxSpeed) {
			this.velocity.setX(this.velocity.getX()
					- Math.sin(this.angle * Math.PI / 180)
					* this.acceleration.getX());
			this.velocity.setY(this.velocity.getY()
					+ Math.cos(this.angle * Math.PI / 180)
					* this.acceleration.getY());

		}
		System.out.println("accelerate");
	}

	/**
	 * decreases the speed of the player entity 
	 */
	public void decelerate() {
		this.velocity.setX(this.velocity.getX() * this.friction);
		this.velocity.setY(this.velocity.getY() * this.friction);
	}

	/**
	 * renders the single vertices and draws a line between them
	 */
	@Override
	public void render(Graphics2D g) {

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
		// TODO Auto-generated method stub
		
	}

	
}