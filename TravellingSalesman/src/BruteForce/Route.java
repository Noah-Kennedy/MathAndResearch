package BruteForce;

public class Route {
	protected double time;
	public Route(double t){
		time = t;
	}
	public double getTime(){
		double t = time;
		return t;
	}
	public String toString(){
		return "Time is " + time;
	}
}
