package factorials.gui;

import factorials.model.Circles;
import factorials.model.HTree;
import factorials.model.Mandelbrot;
import factorials.model.Tree;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Shahriar (Shawn) Emami
 * @date Jan 30, 2018
 * 
 * @see <a href="http://natureofcode.com/book/chapter-8-fractals/">Half Cyrcle and other fractals</a>
 * @see <a href="http://introcs.cs.princeton.edu/java/23recursion/AnimatedHtree.java.html">HTree</a>
 * @see <a href="http://introcs.cs.princeton.edu/java/32class/Mandelbrot.java.html">Mandelbrot</a>
 * @see <a href="http://jonisalonen.com/2013/lets-draw-the-mandelbrot-set/">Mandelbrot</a>
 */
public class Fractals extends Application {

	private GraphicsContext gc;
	private Canvas canvas;
	private int lastType, lastDepth;
	private double lastScale;

	@Override
	public void start( Stage primaryStage) throws Exception {
		BorderPane rootPane = new BorderPane();

		GridPane gridPane = new GridPane();
		gridPane.setHgap( 10);
		gridPane.setVgap( 3);
		gridPane.setPadding( new Insets( 3, 3, 3, 3));
		rootPane.setTop( gridPane);

		BorderPane canvasPane = new BorderPane();
		canvasPane.setStyle( "-fx-background-color: LightGray;");
		rootPane.setCenter( canvasPane);

		canvas = new Canvas();
		canvasPane.setCenter( canvas);

		createDetailRow( gridPane, "Circles", 0, 0, 20, 6, 20, 4, 1);
		createDetailRow( gridPane, "HTree", 1, 1, 20, 6, 20, 4, 1);
		createDetailRow( gridPane, "Mandelbrot", 2, 2, 10000, 20, 10000, 300, 50);
		createDetailRow( gridPane, "Tree", 3, 3, 20, 10, 20, 5, 0.25);

		gc = canvas.getGraphicsContext2D();

		Scene scene = new Scene( rootPane, 1000, 600);
		primaryStage.setScene( scene);
		primaryStage.setTitle( "Fractals");
		// scene.getStylesheets().add( Login.class.getResource( "/css/Fractals.css").toExternalForm());
		primaryStage.show();

		canvas.widthProperty().bind( rootPane.widthProperty());
		canvas.heightProperty().bind( rootPane.heightProperty().subtract( gridPane.getHeight()));
		canvas.widthProperty().addListener( ( ob, oldV, newV) -> {
			stop( lastType);
			start( lastType, lastDepth, lastScale);
		});
		canvas.heightProperty().addListener( ( ob, oldV, newV) -> {
			stop( lastType);
			start( lastType, lastDepth, lastScale);
		});
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		Circles.stopLoop();
		HTree.stopLoop();
		Mandelbrot.stopLoop();
		Tree.stopLoop();
	}

	private void createDetailRow( GridPane gridPane, String name, final int type, int row, int maxD, int currentDepth,
			int maxS, double currentScale, double incS) {
		Label lName = new Label( name);
		gridPane.add( lName, 0, row);
		Button btStart = new Button( "Start");
		btStart.setMaxWidth( Double.MAX_VALUE);
		gridPane.add( btStart, 1, row);
		Button btStop = new Button( "Stop");
		btStop.setMaxWidth( Double.MAX_VALUE);
		gridPane.add( btStop, 2, row);
		Button btClear = new Button( "Clear");
		btClear.setMaxWidth( Double.MAX_VALUE);
		gridPane.add( btClear, 3, row);
		Label lDepth = new Label( "Depth");
		gridPane.add( lDepth, 4, row);
		Spinner< Integer> spDepth = new Spinner<>( 0, maxD, currentDepth);
		gridPane.add( spDepth, 5, row);
		Label lScale = new Label( "Scale");
		gridPane.add( lScale, 6, row);
		Spinner< Double> spScale = new Spinner<>( 0, maxS, currentScale, incS);
		gridPane.add( spScale, 7, row);
		Label lValue = new Label( "");
		gridPane.add( lValue, 8, row);
		btClear.setOnAction( e -> {
			stop( type);
			lastType = -1;
			gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		});
		btStart.setOnAction( e -> {
			lValue.setText( String.format( "Depth: %s, Scale: %s, Width: %s, Height: %s", spDepth.getValue(),
					spScale.getValue(), canvas.getWidth(), canvas.getHeight()));
			lastType = type;
			lastDepth = spDepth.getValue();
			lastScale = spScale.getValue();
			start( type, spDepth.getValue(), spScale.getValue());
		});
		btStop.setOnAction( e -> stop( type));
	}

	private void start( final int type, int depth, double scale) {
		switch ( type) {
			case 0:
				circles( depth, scale, canvas.getWidth(), canvas.getHeight());
				break;
			case 1:
				hTree( depth, scale, canvas.getWidth(), canvas.getHeight());
				break;
			case 2:
				mandelbrot( depth, scale, canvas.getWidth(), canvas.getHeight());
				break;
			case 3:
				tree( depth, scale, canvas.getWidth(), canvas.getHeight());
				break;
			default:
				//ignore
		}
	}

	private void stop( final int type) {
		switch ( type) {
			case 0:
				Circles.stopLoop();
				break;
			case 1:
				HTree.stopLoop();
				break;
			case 2:
				Mandelbrot.stopLoop();
				break;
			case 3:
				Tree.stopLoop();
				break;
			default:
				//ignore
		}
	}

	private void circles( int depth, double scale, double width, double height) {
		gc.clearRect( 0, 0, width, height);
		gc.setStroke( Color.MAGENTA);
		gc.setLineWidth( 2);
		Circles.startAnimation( gc, depth, width / 2, height / 2, width / scale);
		Circles.startLoop();
	}

	private void hTree( int depth, double scale, double width, double height) {
		gc.clearRect( 0, 0, width, height);
		gc.setStroke( Color.MAGENTA);
		gc.setLineWidth( 2);
		HTree.startAnimation( gc, depth, width / 2, height / 2, width / scale);
		HTree.startLoop();
	}

	private void mandelbrot( int depth, double zoom, double width, double height) {
		gc.clearRect( 0, 0, width, height);
		Mandelbrot.startAnimation( gc.getPixelWriter(), depth, zoom, width, height);
		Mandelbrot.startLoop();
	}

	private void tree( int depth, double scale, double width, double height) {
		gc.clearRect( 0, 0, width, height);
		gc.setStroke( Color.MAGENTA);
		gc.setLineWidth( 2);
		Tree.startAnimation( gc, depth, width / 2, height, height / scale);
		Tree.startLoop();
	}

	public static void main( String[] args) {
		launch( args);
	}
}
