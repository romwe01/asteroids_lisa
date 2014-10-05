package entities;

import java.util.Arrays;

public class Vector2D {
	private static final int X = 0;
	private static final int Y = 1;
	private double[] vec;

	public Vector2D(double x, double y) {
		this.vec = new double[] { x, y };
	}

	public double getX() {
		return this.vec[Vector2D.X];
	}

	public double getY() {
		return this.vec[Vector2D.Y];
	}

	public void setX(double x) {
		this.vec[Vector2D.X] = x;
	}

	public void setY(double y) {
		this.vec[Vector2D.Y] = y;
	}
	
	public Vector2D get() {
		return this;
	}
	
	public void set(double x, double y) {
		this.vec[Vector2D.X] = x;
		this.vec[Vector2D.Y] = y;
	}

	public double length() {
		return Math.sqrt((this.getX() * this.getX())
				+ (this.getY() * this.getY()));
	}

	public Vector2D normalized() {
		double length = this.length();
		return new Vector2D((this.getX() / length), (this.getY() / length));
	}

	public Vector2D add(Vector2D other) {
	//	return new Vector2D(this.getX() + other.getX(), this.getY()
	//			+ other.getY());
		this.set(this.getX() + other.getX(), this.getY() + other.getY());
		return this;
        
		
	}

	public Vector2D subtract(Vector2D other) {
		return new Vector2D(this.getX() - other.getX(), this.getY()
				- other.getY());
	}

	public double dot(Vector2D other) {
		return (this.getX() * other.getX()) + (this.getY() * other.getY());
	}

	public double perpDot(Vector2D other) {
		return (-this.getY() * other.getX()) + (this.getX() * other.getY());
	}

	public Vector2D rotated(double phi) {
		double cos = Math.cos(phi);
		double sin = Math.sin(phi);

		double x = this.getX() * cos - this.getY() * sin;
		double y = this.getX() * sin + this.getY() * cos;

		return new Vector2D(x, y);
	}

	public Vector2D scaled(Vector2D scale){
		return new Vector2D(this.getX() * scale.getX(), this.getY() * scale.getY());
	}

	public Vector2D translated(Vector2D trans){
		return new Vector2D(this.getX() + trans.getX(), this.getY() + trans.getY());
	}
	
	public String toString() {
		return Arrays.toString(this.vec);
	}
}
