package factorials.model;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Tree extends AnimationTimer {

	private GraphicsContext gc;
	private double x, y;
	private double length;
	private int depth;

	private static Tree t;

	private Tree() {
		super();
	}

	public static void startAnimation(GraphicsContext gc, int depth, double x, double y, double length) {
		if (t == null) {
			t = new Tree();
		} else {
			t.stop();
		}
		t.gc = gc;
		t.x = x;
		t.y = y;
		t.length = length;
		t.depth = depth;
	}

	public static void startLoop() {
		if (t != null) {
			t.start();
		}
	}

	public static void stopLoop() {
		if (t != null) {
			t.stop();
		}
	}

	@Override
	public void handle(long now) {
		drawTree(x, y, -90, depth, depth);
	}

	void drawTree(double x1, double y1, float angle, int depth, double lw) {
		if (depth <= 0) {
			return;
		}
        double x2 = x1 + (Math.cos(Math.toRadians(angle)) * depth * 10.0);
        double y2 = y1 + (Math.sin(Math.toRadians(angle)) * depth * 10.0);
		gc.setLineWidth(lw);
		gc.strokeLine(x1, y1, x2, y2);
		lw--;
		drawTree(x2, y2, angle - 20, depth - 1, lw);
		drawTree(x2, y2, angle + 20, depth - 1, lw);
	}
}
