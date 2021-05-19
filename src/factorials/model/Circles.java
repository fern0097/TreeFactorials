package factorials.model;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Circles extends AnimationTimer{

	private static final long SECOND = 1000000000L;

	private GraphicsContext gc;
	private double x, y;
	private double r;
	private int targetDepth;
	private int startDepth;

	private long lastUpdated;

	private static Circles ht;

	@Override
	public void handle( long now){
		if( now - lastUpdated < SECOND / 2){
			return;
		}
		lastUpdated = now;
		if( startDepth <= targetDepth)
			circles( 0, startDepth, ++startDepth, x, y, r);
		else
			stop();
	}

	private Circles(){
		super();
	}

	public static void startAnimation( GraphicsContext gc, int targetDepth, double x, double y, double r){
		if( ht == null){
			ht = new Circles();
		}else{
			ht.stop();
		}
		ht.gc = gc;
		ht.x = x;
		ht.y = y;
		ht.r = r;
		ht.startDepth = 0;
		ht.targetDepth = targetDepth;
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

	private void circles( int depth, final int startDepth, final int targetDepth, double x, double y, double r){
		if( depth >= targetDepth){
			return;
		}

		if( depth >= startDepth)
			gc.strokeOval( x - r, y - r, r * 2, r * 2);

		circles( depth + 1, startDepth, targetDepth, x + r, y, r / 2);// right
		circles( depth + 1, startDepth, targetDepth, x - r, y, r / 2);// left
		circles( depth + 1, startDepth, targetDepth, x, y + r, r / 2);// down
		circles( depth + 1, startDepth, targetDepth, x, y - r, r / 2);// up
	}
}