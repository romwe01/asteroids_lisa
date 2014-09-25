package collision;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.Vector2D;



public abstract class BoundingCircle {

	
	private Vector2D center;
	private int radius;
	
	public BoundingCircle(Vector2D center, int radius2){
		super();
		
		this.center = center;
		this.radius = radius2;
	}
	
	public void drawBounding(Graphics2D g){
		g.setColor(Color.magenta);
		g.drawArc((int)center.getX(), (int)center.getY(), radius, radius, 0, 360);
	}
	
	public abstract void collided(BoundingCircle col);
	
	public void updateCollider(Vector2D center){
		this.center = center;
	}
	
	public BoundingCircle getCollisionObject(){
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
		// TODO Auto-generated method stub
		return center;
	}
	
}
