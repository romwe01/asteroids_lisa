package entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import core.Core;
import statemachine.PlayState;
import vector.Vector2D;
import collider.CircleCollider;

public abstract class Entity extends CircleCollider{

	private String name;
	protected Core core;
	protected PlayState state;
	
	protected Vector2D velocity;
	protected Vector2D scale;
	protected Vector2D position;
	protected float angle;
	protected float speed;
	protected ArrayList<Vector2D> vertices;
	protected ArrayList<Vector2D> verticesTrans;
	
	
	public Entity(String name, PlayState state, Core core, float x, float y, int radius) {
		//constructor of collider
		super(new Vector2D(x,y) , radius);
		this.core = core;
		this.state = state;
		this.setName(name);
		
		this.angle = 180f;
		this.speed = 200;
		this.position = new Vector2D(x, y);
		this.scale = new Vector2D(2,2);
		this.velocity = new Vector2D(0,0);
		this.vertices = new ArrayList<Vector2D>();
		this.verticesTrans = new ArrayList<Vector2D>();
	}
	
	/**
	 * Transforms the vertices into new shape.
	 */
	protected void transform(){
		this.verticesTrans.clear();
		
		for (Vector2D vt : this.vertices){
			vt = vt.scaled(this.scale);
			vt = vt.rotated(this.angle * Math.PI /180);
			vt = vt.translated(this.position);
			this.verticesTrans.add(vt);
		}
	}
	
	/**
	 * Renders the vertices of entity. Draws lines between the vertices. 
	 * @param g
	 */
	protected void render(Graphics2D g){
		if (!this.verticesTrans.isEmpty()){
			Vector2D lastVt = this.verticesTrans.get(this.vertices.size() - 1);
			for (Vector2D vt : this.verticesTrans){
				g.drawLine((int)vt.getX(), (int)vt.getY(), (int)lastVt.getX(), (int)lastVt.getY());
				lastVt = vt;
			}
		}
		//super.drawCollider(g);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void updateCollider(float x, float y){
		super.updateCollider(x, y);
	}
	
	public void updateCollider(float x, float y, int radius){
		super.updateCollider(x, y, radius);
	}
	
	/**
	 * Simple position change. 
	 */
	public void update(){
		Vector2D vel = new Vector2D(this.velocity.getX() * core.getDT(), this.velocity.getY() * core.getDT());
		this.position.add(vel);
	}
	
	public Vector2D getPosition(){return this.position;};
	public void setPosition(float x, float y){this.position.set(x,y);};
	
	public void drawCollider(Graphics2D g){
		super.drawCollider(g);
	}
	
	public CircleCollider getCollisionObject(){
		return super.getCollisionObject();
	}
	

}
