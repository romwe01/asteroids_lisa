package entities;

import java.awt.Graphics2D;

import statemachine.PlayState;
import vector.Vector2D;
import core.Core;
import collider.CircleCollider;

public class Shot extends Entity {

	private static int radius = 2;
	private boolean alive = true;
	

	public Shot(String name,PlayState state, Core core, float x, float y, float rotation) {
		super(name,state, core, x, y, radius);
		
		this.angle = rotation;
		this.speed = 700;
		
		//shape
		this.vertices.add(new Vector2D(0,0));
		this.vertices.add(new Vector2D(0,1));
		this.vertices.add(new Vector2D(1,1));
		this.vertices.add(new Vector2D(1,0));
		
		//collider
		state.collisionT.addBullet(getCollisionObject());
		
	}

	public void setRotation(float rotation){
		this.angle = rotation;
	}
	
	public void fire(){
		this.velocity = new Vector2D(-Math.sin(this.angle * Math.PI /180) * this.speed, Math.cos(this.angle*Math.PI /180)*this.speed);
	}
	
	public void update(){
		super.update();
		if (this.position.getY() > core.graphicsConfig.Height /2 || this.position.getY() < -core.graphicsConfig.Height /2 
				|| this.position.getX() > core.graphicsConfig.Width /2 || this.position.getX() < -core.graphicsConfig.Width /2){
			try {
				this.finalize();
			} catch (Throwable e){
				e.printStackTrace();
			}
		}
		this.transform();
		super.updateCollider((float)this.getPosition().getX(), (float)this.getPosition().getY());
		
	}
	
	@Override
	public void collided(CircleCollider col) {
		alive = false;
		
	}
	
	public void render(Graphics2D g){
		if (isAlive() && alive){
			super.render(g);
		}
		else{
			state.collisionT.removeBullet(getCollisionObject());
			state.entityManager.removeEntity(this);
			//System.out.println("bullet zerstört");
		}
	}

	/**
	 * checks if bullet is in the window
	 * @return
	 */
	private boolean isAlive() {
		return !(this.position.getX() < (-core.graphicsConfig.Width/2 - radius) || this.position.getX() > (core.graphicsConfig.Width/2 + radius) 
				|| this.position.getY()< (-core.graphicsConfig.getHeight()/2 - radius) || this.position.getY() > (core.graphicsConfig.getHeight()/2 + radius));
	}

}
