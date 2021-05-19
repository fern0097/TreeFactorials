package factorials.model;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class HTree extends AnimationTimer{

	private GraphicsContext gc;
	private double x, y;
	private double width;
	private int depth;

	private static HTree ht;

	@Override
	public void handle( long now){
		hTree( depth, x, y, width);
	}

	private HTree(){
		super();
	}

	public static void startAnimation( GraphicsContext gc, int depth, double x, double y, double width){
		if( ht == null){
			ht = new HTree();
		}else{
			ht.stop();
		}
		ht.gc = gc;
		ht.x = x;
		ht.y = y;
		ht.width = width;
		ht.depth = depth;
	}

	public static void startLoop(){
		if( ht != null){
			ht.start();
		}
	}

	public static void stopLoop(){
		if( ht != null){
			ht.stop();
		}
	}

	void hTree( int depth, double x, double y, double width){
		if( depth == 0){
			stop();
			return;
		}

		// compute the coordinates of the 4 tips of the H
		double xLeft = x - width / 1, xRight = x + width / 1;
		double yTop = y - width / 2, yButtom = y + width / 2;

		// draw the H, centered on (x, y) of the given side length
		gc.strokeLine( xLeft, y, xRight, y);
		gc.strokeLine( xLeft, yTop, xLeft, yButtom);
		gc.strokeLine( xRight, yTop, xRight, yButtom);

		depth--;
		width /= 2;
		hTree( depth, xLeft, yTop, width); // upper left
		hTree( depth, xLeft, yButtom, width); // lower left
		hTree( depth, xRight, yButtom, width); // lower right
		hTree( depth, xRight, yTop, width); // upper right
	}
}
