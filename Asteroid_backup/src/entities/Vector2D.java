package entities;

import java.util.Arrays;

public class Vector2D {
	private static final int X = 0;
	private static final int Y = 1;
	private double[] vec;

	//setting the vector and its x, y
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

	/**
	 * calculates the length of vector
	 * @return length of vector
	 */
	public double length() {
		return Math.sqrt((this.getX() * this.getX())
				+ (this.getY() * this.getY()));
	}

	/**
	 * normalizes the vector
	 * @return normalized vector
	 */
	public Vector2D normalized() {
		double length = this.length();
		return new Vector2D((this.getX() / length), (this.getY() / length));
	}

	/**
	 * adds a vector to another one
	 * @param other vector to add
	 * @return new vector
	 */
	public Vector2D add(Vector2D other) {
	//	return new Vector2D(this.getX() + other.getX(), this.getY()
	//			+ other.getY());
		this.set(this.getX() + other.getX(), this.getY() + other.getY());
		return this;
	}

	/**
	 * subracts a vector from another one
	 * @param other subtrahend
	 * @return
	 */
	public Vector2D subtract(Vector2D other) {
		return new Vector2D(this.getX() - other.getX(), this.getY()
				- other.getY());
	}

	/**
	 * cross product
	 * @param other
	 * @return
	 */
	public double dot(Vector2D other) {
		return (this.getX() * other.getX()) + (this.getY() * other.getY());
	}

	//crossproduct
	public double perpDot(Vector2D other) {
		return (-this.getY() * other.getX()) + (this.getX() * other.getY());
	}

	/**
	 * rotates a vector with the angle phi
	 * @param phi
	 * @return
	 */
	public Vector2D rotated(double phi) {
		double cos = Math.cos(phi);
		double sin = Math.sin(phi);

		double x = this.getX() * cos - this.getY() * sin;
		double y = this.getX() * sin + this.getY() * cos;

		return new Vector2D(x, y);
	}

	/**
	 * scales a vector
	 * @param scale
	 * @return
	 */
	public Vector2D scaled(Vector2D scale){
		return new Vector2D(this.getX() * scale.getX(), this.getY() * scale.getY());
	}

	/**
	 * translates a vector
	 * @param trans
	 * @return
	 */
	public Vector2D translated(Vector2D trans){
		return new Vector2D(this.getX() + trans.getX(), this.getY() + trans.getY());
	}
	
	public String toString() {
		return Arrays.toString(this.vec);
	}
}
