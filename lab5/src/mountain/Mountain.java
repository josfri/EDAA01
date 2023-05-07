package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;
import java.awt.Point;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;

	/**
	 * Creates an object that handles Mountain Fractal.
	 * 
	 * @param 3 points of triangle, dev
	 */
	public Mountain(Point a, Point b, Point c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;

	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */

	@Override
	public String getTitle() {
		return "Straight Mountain Fractal";
	}

	/**
	 * Draws the fractalTriangle.
	 * 
	 * @param turtle the turtle graphic object
	 */
	@Override
	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, order, a, b, c);
	}

	/*
	 * Recursive method: Draws a recursive triangle based of 3 points.
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {

			Point ab = middlePoint(a, b);
			Point ac = middlePoint(a, c);
			Point bc = middlePoint(b, c);

			fractalTriangle(turtle, order - 1, a, ab, ac);
			fractalTriangle(turtle, order - 1, ab, b, bc);
			fractalTriangle(turtle, order - 1, ac, bc, c);
			fractalTriangle(turtle, order - 1, ab, bc, ac);
		}
	}

	public static double randFunc(double dev) {

		double t = dev * Math.sqrt(-2 * Math.log(Math.random()));

		if (Math.random() < 0.5) {
			t = -t;
		}
		return t;
	}

	private Point middlePoint(Point a, Point b) {

		double x = 0;
		double y = 0;

		x = (b.getX() + a.getX()) / 2;

		y = (b.getY() + a.getY()) / 2;

		Point mid = new Point((int) x, (int) y);
		return mid;
	}

}
