package entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import collision.CircleCollider;

import core.Core;

public abstract class Entity extends CircleCollider{
	
	protected Vector2D position;
	protected ArrayList<Vector2D> vertices = new ArrayList<Vector2D>();
	protected ArrayList<Vector2D> verticesTrans = new ArrayList<Vector2D>();
	protected Vector2D velocity = new Vector2D(0,0);
	protected float angle = 180f;
	protected Vector2D scale = new Vector2D(2,2);
	
	
	private String name;
	protected Core core;
	
	//calls constructor of collider
	public Entity(String name, Core core, float d, float e, int radius){
		super(new Vector2D(d,e), radius);
		this.name = name;
		this.core = core;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
	
	public abstract Vector2D getPosition();
	
	public abstract void setPosition(float x, float y);
	
	public void drawCollider(Graphics2D g){
		super.drawBounding(g);
	}
	
	public CircleCollider getCollisionObject(){
		return super.getCollisionObject();
	}
	
	public void updateCollider(Vector2D center){
		super.updateCollider(center);
	}
}
