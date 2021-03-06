package entities;

import java.awt.Graphics2D;

import collision.CircleCollider;
import core.Core;

public class Shot extends Entity {

	private float bulletSpeed = 700;
	private static int radius = 2;
	private boolean alive = true;

	public Shot(String name, Core core, float d, float e, float rotation) {
		super(name, core, d, e, radius);

		position = new Vector2D(d, e);
		angle = rotation;

		// shape
		vertices.add(new Vector2D(0, 0));
		vertices.add(new Vector2D(0, 1));
		vertices.add(new Vector2D(1, 1));
		vertices.add(new Vector2D(1, 0));
		
		//collider
		core.cT.addBulletObject(getCollisionObject());
	}

	public void setRotation(float rotation) {
		this.angle = rotation;
	}

	/**
	 * calls all methods for transforming the bullet
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
	 * sets the velocity of the flying bullet
	 */
	public void fire() {
		this.velocity = new Vector2D(-Math.sin(this.angle * Math.PI / 180)
				* bulletSpeed, Math.cos(this.angle * Math.PI / 180)
				* bulletSpeed);
	}

	@Override
	/**
	 * updates the position of the bullet
	 */
	public void update() {

		
		if (this.position.getY() > core.graphicsConfig.Height / 2
				|| this.position.getY() < -core.graphicsConfig.Height / 2
				|| this.position.getX() > core.graphicsConfig.Width / 2
				|| this.position.getX() < -core.graphicsConfig.Width / 2) {
			try {
				this.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} 
		
		// Velocity / Geschwindigkeit
		Vector2D vel = new Vector2D(this.velocity.getX() * core.getDT(),this.velocity.getY() * core.getDT());
		this.position.set(this.position.getX() + vel.getX(),this.position.getY() + vel.getY());
		this.transform();
		Vector2D c = getPosition();
		super.updateCollider(new Vector2D((float)c.getX() - radius/2.0f, (float)c.getY() - radius/2.0f));
	}

	 
	/**
	 * living bullets are rendered, dead bullets are removed from entitymanager
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
		else{
			core.entityManager.removeEntity(this);
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
