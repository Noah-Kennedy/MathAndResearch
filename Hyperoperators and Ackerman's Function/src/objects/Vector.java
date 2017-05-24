package objects;

public class Vector {
	private double x;
	private double y;
	private double m;
	private double theta;
	
	public Vector(double xIn, double yIn){
		x = xIn;
		y = yIn;
		m = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		theta = Math.atan(y / x);
	}
}
