package entities;

import java.awt.Graphics2D;

import core.Core;

public class Asteroid extends Entity{

	public Asteroid(String name, Core core, double d, double e) {
		super(name, core, d, e);
		
		position = new Vector2D(d, e);
		
		// shape
		vertices.add(new Vector2D(0,0));
		vertices.add(new Vector2D(0,1.5));
		vertices.add(new Vector2D(1.5,2));
		vertices.add(new Vector2D(2,1));
		vertices.add(new Vector2D(1,0));
		double rnd = (Math.random()*70)+25;
		scale = new Vector2D(rnd,rnd);
	}
	
	private void transform() {
		
		this.verticesTrans.clear();
		
		for (Vector2D vt : this.vertices){
			vt = vt.scaled(this.scale);
			vt = vt.rotated(this.angle * Math.PI / 180);
			vt = vt.translated(this.position);
			this.verticesTrans.add(vt);
		}	
	}

	@Override
	public void update() {
		// Velocity / Geschwindigkeit
				Vector2D vel = new Vector2D(this.velocity.getX() * core.getDT(),
						this.velocity.getY() * core.getDT());

				this.position.set(this.position.getX() + vel.getX(),
						this.position.getY() + vel.getY());



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
