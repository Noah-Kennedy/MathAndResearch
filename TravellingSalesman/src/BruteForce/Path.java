package BruteForce;

import java.util.ArrayList;

public class Path extends Route {
	private ArrayList<Route> routes;
	public Path(){
		super(0);
		routes = new ArrayList<Route>();
	}
	public void add(ArrayList<Route> r){
		for(int i = 0; i < r.size(); i++){
			routes.add(r.get(i));
			time += r.get(i).getTime();
		}
	}
	public void add(Route r){
		routes.add(r);
	}
	public void set(ArrayList<Route> r){
		routes.clear();
		this.add(r);
	}
}
