package collision;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.Vector2D;


public abstract class CircleCollider {

	
	private Vector2D center;
	private int radius;
	
	public CircleCollider(Vector2D center, int radius2){
		super();
		
		this.center = center;
		this.radius = radius2;
	}
	
	/**
	 * draws the collider in magenta, for check purpose only
	 * @param g
	 */
	public void drawBounding(Graphics2D g){
		g.setColor(Color.magenta);
		g.drawArc((int)center.getX(), (int)center.getY(), radius, radius, 0, 360);
	}
	
	/**
	 * handles what should happen if two objects collide
	 * @param col
	 */
	public abstract void collided(CircleCollider col);
	
	public void updateCollider(Vector2D center){
		this.center = center;
	}
	
	public CircleCollider getCollisionObject(){
		return this;
	}

	public void setCenter(Vector2D center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Vector2D getCenter() {
		return center;
	}
	
}
