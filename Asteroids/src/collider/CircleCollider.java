package collider;

import java.awt.Color;
import java.awt.Graphics2D;

import vector.Vector2D;

public abstract class CircleCollider {

	public Vector2D center;
	public int radius;
	
	public CircleCollider(Vector2D center, int radius) {
		super();
		this.center = center;
		this.radius = radius;
	}

	public void updateCollider(float x, float y){
		center.set(x, y);
	}
	
	public void updateCollider(float x, float y, int radius){
		center.set(x, y);
		this.radius = radius;
	}
	
	/**
	 * handles what should happen if object collides
	 * @param col
	 */
	public abstract void collided(CircleCollider col);
	
	/**
	 * draws the collider in magenta, for check purpose only
	 * @param g
	 */
	public void drawCollider(Graphics2D g){
		g.setColor(Color.magenta);
		g.drawArc((int)(center.getX() - radius/2.0), (int)(center.getY() - radius/2.0), radius, radius, 0, 360);
	}
	
	public CircleCollider getCollisionObject(){
		return this;
	}
	
}
