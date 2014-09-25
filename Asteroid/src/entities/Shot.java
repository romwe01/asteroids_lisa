package entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import core.Core;

public class Shot extends Entity {

	private float bulletSpeed = 300;

	public Shot(String name, Core core, double d, double e, float rotation) {
		super(name, core, d, e);

		position = new Vector2D(d, e);
		angle = rotation;

		// shape
		vertices.add(new Vector2D(0, 0));
		vertices.add(new Vector2D(0, 1));
		vertices.add(new Vector2D(1, 1));
		vertices.add(new Vector2D(1, 0));
	}

	public void setRotation(float rotation) {
		this.angle = rotation;
	}

	private void transform() {

		this.verticesTrans.clear();

		for (Vector2D vt : this.vertices) {
			vt = vt.scaled(this.scale);
			vt = vt.rotated(this.angle * Math.PI / 180);
			vt = vt.translated(this.position);
			this.verticesTrans.add(vt);
		}
	}

	public void fire() {
		this.velocity = new Vector2D(-Math.sin(this.angle * Math.PI / 180)
				* bulletSpeed, Math.cos(this.angle * Math.PI / 180)
				* bulletSpeed);
	}

	@Override
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
	}

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
