package mountain;

import java.awt.Point;
import java.util.HashMap;
import fractal.Fractal;
import fractal.TurtleGraphics;

public class IrregularMountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private Double dev;
	private HashMap<Side, Point> sides;

	/**
	 * Creates an object that handles Mountain Fractal.
	 * 
	 * @param 3 points of triangle, dev
	 */
	public IrregularMountain(Point a, Point b, Point c, Double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		sides = new HashMap<Side, Point>();

	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */

	@Override
	public String getTitle() {
		return "Irregular Mountain Fractal";
	}

	/**
	 * Draws the fractalTriangle.
	 * 
	 * @param turtle the turtle graphic object
	 */
	@Override
	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, order, a, b, c, dev);
	}

	/*
	 * Recursive method: Draws a recursive triangle based of 3 points.
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {

			double offset1 = RandomUtilities.randFunc(dev);
			double offset2 = RandomUtilities.randFunc(dev);
			double offset3 = RandomUtilities.randFunc(dev);
			dev /= 2;

			Point ab = middlePoint(a, b, offset1);
			Point ac = middlePoint(a, c, offset2);
			Point bc = middlePoint(b, c, offset3);

			fractalTriangle(turtle, order - 1, a, ab, ac, dev);
			fractalTriangle(turtle, order - 1, ab, b, bc, dev);
			fractalTriangle(turtle, order - 1, ac, bc, c, dev);
			fractalTriangle(turtle, order - 1, ab, bc, ac, dev);
		}
	}

	private Point middlePoint(Point a, Point b, Double offset) {

		Side side = new Side(a, b);

		if (sides.containsKey(side)) {
			Point temp = sides.get(side);
			sides.remove(side);
			return temp;
		} else {

			double x = 0;
			double y = 0;

			x = (b.getX() + a.getX()) / 2;

			y = (b.getY() + a.getY()) / 2;

			Point mid = new Point((int) x, (int) (y + offset));
			sides.put(side, mid);

			return mid;
		}
	}

}
