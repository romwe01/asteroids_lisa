package entities;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import core.Core;

public class Ship extends Entity {
	
	private Vector2D position;
	private float radius;
	private ArrayList<Vector2D> vertices = new ArrayList<Vector2D>();
	private ArrayList<Vector2D> verticesTrans = new ArrayList<Vector2D>();
	private Vector2D velocity = new Vector2D(1,1);
	private Vector2D acceleration = new Vector2D(20,20);
	private Vector2D offset = new Vector2D(100,100);
	private float friction = 0.95f;
	private float angle = 180f;
	private Vector2D scale = new Vector2D(20,20);

	public Ship(String name, Core core, float x, float y, float radius) {
		super(name, core, x, y, radius);
		
		this.position = new Vector2D(x, y);
		this.radius = radius;
		
		// shape
		this.vertices.add(new Vector2D(0,1));
		this.vertices.add(new Vector2D(-1,-1));
		this.vertices.add(new Vector2D(1,-1));
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
	//	this.accelerate();
	//	System.out.println(this.velocity);
	//	this.transform();
		
		System.out.println(this.velocity);
		
        if(this.position.getX() < 0){
        	this.position.setX(core.graphicsConfig.Width);
        } else if (this.position.getX() > core.graphicsConfig.Width){
        	this.position.setX(0);
        }
        if(this.position.getY() < 0){
        	this.position.setY(core.graphicsConfig.Height);
        } else if (this.position.getY() > core.graphicsConfig.Height){
        	this.position.setY(0);
        }

         // Velocity / Geschwindigkeit
        Vector2D vel = new Vector2D(this.velocity.getX() * core.getDT(), this.velocity.getY() * core.getDT());
    //    Vector2D vel = new Vector2D(this.velocity.getX(), this.velocity.getY());
        
        System.out.println(vel);
        System.out.println(this.position.getX() + ", " + this.position.getY()); 
     //   this.position.add(vel);
        this.position.set(this.position.getX() + vel.getX(), this.position.getY() + vel.getY());
        
        System.out.println(this.position.getX() + ", " + this.position.getY());
         // Acceleration / Beschleunigung 
         Vector2D acc = new Vector2D(this.acceleration.getX() * core.getDT(), this.acceleration.getY() * core.getDT());
         
         this.velocity.add(acc);

         this.transform();
	}
	
	public void transform() {
		this.verticesTrans.clear();
		
		for (Vector2D vt : this.vertices){
			vt = vt.scaled(this.scale);
			vt = vt.rotated(this.angle * Math.PI / 180);
			vt = vt.translated(this.position);
			this.verticesTrans.add(vt);
		}
	}
	
	public void accelerate(){
		if(this.velocity.getX() < 500 && this.velocity.getX() > -500 && this.velocity.getY() < 500 && this.velocity.getY() > -500){
			this.velocity.setX(this.velocity.getX() - Math.sin(this.angle * Math.PI / 180) * this.acceleration.getX());
            this.velocity.setY(this.velocity.getY() + Math.cos( this.angle * Math.PI / 180) * this.acceleration.getY());
	
		}
            }
	
	public void decelerate(){
		this.velocity.setX(this.velocity.getX() * this.friction);
		this.velocity.setY(this.velocity.getY() * this.friction);
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if (!this.verticesTrans.isEmpty()){
			Vector2D lastVt = this.verticesTrans.get(this.vertices.size()-1);
			
			for (Vector2D vt : this.verticesTrans){
				g.drawLine((int)vt.getX(), (int)vt.getY(), (int)lastVt.getX(), (int)lastVt.getY());
				lastVt = vt;
			//	System.out.println(vt.getX() + ", " + vt.getY());
			}
			
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
	
}