package factorials.util;

/**
 * 
 * @author Shahriar (Shawn) Emami
 * @date Sep 24, 2017
 * 
 * @see <a href="http://introcs.cs.princeton.edu/java/32class/Complex.java.html">Complex class with more details</a>
 */
public class Complex{

	private final double real;
	private final double img;

	public Complex( double real, double img){
		this.real = real;
		this.img = img;
	}

	public double getReal(){
		return real;
	}

	public double getImg(){
		return img;
	}

	public double abs(){
		return Math.hypot( real, img);
	}

	/**
	 * 
	 * @param other
	 * @return
	 * @see <a href="http://mathworld.wolfram.com/ComplexMultiplication.html">Complex Multiplication</a>
	 */
	public Complex times( Complex other){
		return new Complex( real * other.real - img * other.img, real * other.img + img * other.real);
	}

	/**
	 * 
	 * @param other
	 * @return
	 * @see <a href="http://mathworld.wolfram.com/ComplexDivision.html">Complex Division</a>
	 */
	public Complex divide( Complex other){
		double denominator = other.img * other.img + other.img * other.img;
		double real = (this.real * other.real + this.img * other.img) / denominator;
		double img = (this.img * other.real + this.real * other.img) / denominator;
		return new Complex( real, img);
	}

	/**
	 * 
	 * @param other
	 * @return
	 * @see <a href="http://mathworld.wolfram.com/ComplexAddition.html">Complex Addition</a>
	 */
	public Complex plus( Complex other){
		return new Complex( real + other.real, img + other.img);
	}

	/**
	 * 
	 * @param other
	 * @return
	 * @see <a href="http://mathworld.wolfram.com/ComplexSubtraction.html">Complex Subtraction</a>
	 */
	public Complex minus( Complex other){
		return new Complex( real - other.real, img - other.img);
	}

	public static double map( double num, double numMin, double numMax, double goalMin, double goalMax){
		return (num - numMin) / (numMax - numMin) * (goalMax - goalMin) + goalMin;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder( real == 0 ? "" : Double.toString( real));
		builder.append( img > 0 ? "+" : "");
		builder.append( img == 0 ? "" : Double.toString( img));
		builder.append( img == 0 ? "" : "i");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits( img);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits( real);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj){
		if( this == obj)
			return true;
		if( obj == null)
			return false;
		if( !(obj instanceof Complex))
			return false;
		Complex other = (Complex) obj;
		if( Double.doubleToLongBits( img) != Double.doubleToLongBits( other.img))
			return false;
		if( Double.doubleToLongBits( real) != Double.doubleToLongBits( other.real))
			return false;
		return true;
	}
}
