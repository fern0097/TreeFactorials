package factorials.model;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class CirclesAnimated extends AnimationTimer {

	private static final long SECOND = 1000000000L;

	private static final int LEFT = 3;
	private static final int RIGHT = 1;
	private static final int UP = 0;
	private static final int DOWN = 2;

	private GraphicsContext gc;
	private double xStart, yStart, rStart;
	private int targetDepth;
	private double x, y, r;
	private int depth, index;
	private long lastUpdated;

	private static CirclesAnimated ht;

	@Override
	public void handle(long now) {
		if (now - lastUpdated < SECOND / 5) {
			return;
		}
		lastUpdated = now;
		if (depth == 0) {
			xStart = x;
			yStart = y;
			rStart = r;
			drawOval(x, y, r);
			index = UP;
			depth++;
		} else if (depth >= targetDepth) {
			x = xStart;
			y = yStart;
			r = rStart;
			depth = 1;
			index++;
			if (index > LEFT) {
				stopLoop();
			}
		} else {
			circles(x, y, r, index);
			depth++;
		}
	}

	private CirclesAnimated() {
		super();
	}

	public static void startAnimation(GraphicsContext gc, int targetDepth, double x, double y, double r) {
		if (ht == null) {
			ht = new CirclesAnimated();
		} else {
			ht.stop();
		}
		ht.gc = gc;
		ht.x = x;
		ht.y = y;
		ht.r = r;
		ht.depth = 0;
		ht.targetDepth = targetDepth;
	}

	public static void startLoop() {
		if (ht != null) {
			ht.start();
		}
	}

	public static void stopLoop() {
		if (ht != null) {
			ht.stop();
		}
	}

	private void circles(double x, double y, double r, int turn) {
		switch( turn) {
			case UP:
				drawOval(x, y - r, r / 2);
				break;
			case RIGHT:
				drawOval(x + r, y, r / 2);
				break;
			case DOWN:
				drawOval(x, y + r, r / 2);
				break;
			case LEFT:
				drawOval(x - r, y, r / 2);
				break;
			default:
				throw new IllegalArgumentException("Turn: \"" + turn + "\" is incalid");
		}
	}

	private void drawOval(double x, double y, double r) {
		gc.strokeOval(x - r, y - r, r * 2, r * 2);
	}
}
