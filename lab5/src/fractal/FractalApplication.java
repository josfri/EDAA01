package fractal;

import koch.Koch;
import mountain.IrregularMountain;
import mountain.Mountain;
import java.awt.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[3];
		fractals[2] = new Koch(300);
		fractals[1] = new Mountain(new Point(100,350), new Point(250,150), new Point(450,400));
		fractals[0] = new IrregularMountain(new Point(50,350), new Point(450,150), new Point(450,500),40.0);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
