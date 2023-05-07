package mountain;

import java.awt.Point;

public class Side {

	private Point a;
	private Point b;

	public Side(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}

	@Override
	public boolean equals(Object x) {
		if (x instanceof Side) {
			Side s = (Side) x;
			if (s.a == a && s.b == b) {
				return true;
			} else if (s.b == a && s.a == b) {
				return true;
			}
		}
		return false;
	}
}
