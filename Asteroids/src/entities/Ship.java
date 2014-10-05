package entities;


import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import score.Score;
import statemachine.PlayState;
import vector.Vector2D;
import collider.CircleCollider;
import core.Core;

public class Ship extends Entity{

	private Vector2D acceleration = new Vector2D(20, 20);
	private float friction = 0.9f;
	private Shot[] shots = new Shot[5];
	private int nextShot = 0;
	private float cooldown= 0;
	private float maxCoolDown = 0.2f;
	public int lives = 3;
	private boolean alive = true;
	
	/**
	 * creates the ship
	 * @param name
	 * @param core 
	 * @param x x-coordinate for collider
	 * @param y y-coordinate for collider
	 * @param radius radius for collider
	 */
	public Ship(String name, PlayState state, Core core, float x, float y, int radius) {
		super(name,state, core, x, y, radius);
		
		//shape
		this.vertices.add(new Vector2D (0,1));
		this.vertices.add(new Vector2D (-1,-1));
		this.vertices.add(new Vector2D (0, -0.5));
		this.vertices.add(new Vector2D (1,-1));
		
		this.scale = new Vector2D (20, 20);
		this.speed = 300f;
		state.collisionT.addPlayer(super.getCollisionObject());
	}
	
	public void setMaxShootCooldown(float maxShootCooldown){
		maxCoolDown = maxShootCooldown;
	}
	
	/**
	 * updates the position of the entity and its collider
	 * checks if entity is inside the window
	 */
	public void update(){
		super.update();
		
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
		
		//bullets
		if (this.cooldown >0){
			this.cooldown -= core.getDT();
		}
		
		this.transform();
		super.updateCollider((float)getPosition().getX(), (float)getPosition().getY());
	}

	/**
	 * in-/decreases the angle of direction
	 * @param clockwise depends on pressed key for in-/decreasing of angle
	 */
	public void rotate(boolean b) {
		if (b){
			this.angle -= 10;
		}
		else{
			this.angle += 10;
		}
		
	}

	/**
	 * increases the speed of the player entity until it reaches maximum speed limit
	 */
	public void accelerate() {
		if (this.velocity.getX() < speed && this.velocity.getX() > -speed && this.velocity.getY() < this.speed && this.velocity.getY() > -this.speed){
			this.velocity.setX(this.velocity.getX() - Math.sin(this.angle * Math.PI/180) * this.acceleration.getX());
			this.velocity.setY(this.velocity.getY() + Math.cos(this.angle * Math.PI/180)*this.acceleration.getY());
		}
		
	}

	/**
	 * decreases the speed of the player entity 
	 */
	public void decelerate() {
		this.velocity.setX(this.velocity.getX() * this.friction);
		this.velocity.setY(this.velocity.getY() * this.friction);
	}

	public void render(Graphics2D g){
		if (alive){
			super.render(g);
		}
		else{
			//TODO: change state to menu or highscore or game over?
			Calendar a = Calendar.getInstance();
			SimpleDateFormat f = new SimpleDateFormat();
			
			core.scores.add(new Score(f.format(a.getTime()).toString(), core.score));
		}
	}
	
	/**
	 * produces bullets 
	 * @return new bullet, null 
	 */
	public Shot shoot() {
		if (this.cooldown >0){
			return null;
		}
		this.cooldown = maxCoolDown;
		
		String uniqueBulletName = "Bullet"  + " " + System.currentTimeMillis();
		shots[nextShot] = new Shot(uniqueBulletName,state, core, (float)getPosition().getX(), (float)getPosition().getY(), this.angle);
		Shot s = shots[nextShot];
		
		nextShot++;
		nextShot %= shots.length;
		return s;
	}

	@Override
	public void collided(CircleCollider col) {
		if (lives > 0){
			lives--;
		}
		else{
			alive = false;
		}
		
	}

	
	
	

}
