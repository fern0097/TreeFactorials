package factorials.model;

import factorials.util.Complex;
import javafx.animation.AnimationTimer;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Mandelbrot extends AnimationTimer {

	private PixelWriter pw;
	private double shiftX, shiftY;
	private double width, height;
	private double zoom;
	private int iterations;
	private int row = 0;

	private static Mandelbrot m;

	@Override
	public void handle( long now) {
		mandelRow( row++);
		if ( row > height) {
			stop();
		}
	}

	@Override
	public void stop() {
		super.stop();
		row = 0;
	}

	private Mandelbrot() {
		super();
	}

	public static void startAnimation( PixelWriter pw, int iterations, double zoom, double width, double height) {
		startAnimation( pw, iterations, zoom, width, height, width / zoom / 3, height / zoom / 2);
	}

	public static void startAnimation( PixelWriter pw, int iterations, double zoom, double width, double height,
			double shiftX, double shiftY) {
		if ( m == null) {
			m = new Mandelbrot();
		} else {
			m.stop();
		}
		m.pw = pw;
		m.zoom = zoom;
		m.iterations = iterations;
		m.width = width;
		m.height = height;
		m.shiftX = shiftX;
		m.shiftY = shiftY;
	}

	public static void startLoop() {
		if ( m != null) {
			m.start();
		}
	}

	public static void stopLoop() {
		if ( m != null) {
			m.stop();
		}
	}

	void mandelbrot() {
		for ( int row = 0; row < height; row++) {
			mandelRow( row);
		}
	}

	void mandelRow( int row) {
		double real, img;
		for ( int col = 0; col < width; col++) {
			real = ( ( col - width) / zoom) + shiftX;
			img = ( ( row - height) / zoom) + shiftY;
			double gray = mand( new Complex( real, img), iterations);
			double t1 = gray / iterations;
			double c1 = Math.min( 1 * 2 * t1, 1);
			double c2 = Math.max( 1 * ( 2 * t1 - 1), 0);
			pw.setColor( col, row, Color.color( c2, c1, c2, 1));
		}
	}

	int mand( Complex z0, int max) {
		Complex z = z0;
		for ( int t = 0; t < max; t++) {
			if ( z.abs() > 2.0) {
				return t + 1;
			}
			z = z.times( z).plus( z0);
		}
		return max;
	}
}
