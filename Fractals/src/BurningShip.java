import java.awt.Color;
import java.awt.Point;


public class BurningShip {
	Canvas mc = new Canvas();
	int counter = 0;
	int max;

	
	public BurningShip(int m){
		mc.setSize(1000, 1000);
		mc.setVisible(true);
		mc.setInkColor(Color.BLACK);
		max = m;
        
	}
	public void plot(double x, double y){
		int xf = (int) ((1 + x) * 334);
		int yf = (int) (-250 * y + 625);
		mc.drawPoint(new Point(xf,yf));
	}
	//center at (-1.75, -0.35)
	public void plotAtCoolShip(double x, double y){
		int xf = (int) ((2 + x) * 2000);
		int yf = (int) ((-50000 * y) - 1250);
		mc.drawPoint(new Point(xf,yf));
	}
	
	public boolean getInSet(double x, double y){
		return this.getInSet(0, 0, x, y);
	}
	
	public boolean getInSet(double x, double y, double cx, double cy){
		double nx = x * x - y * y - cx;
		double ny = 2 * Math.abs(x * y) - cy;
		if(nx * nx - ny * ny >= 4){
			return false;
		}
		else if(counter >= max){
			counter = 0;
			return true;
		}
		else{
			counter++;
			return getInSet(nx,ny,cx,cy);
		}
	}
	
	public void drawSet(){
		for(double i = -1; i <= 2; i += .0025){
			for(double k = -1.5; k <= 2.5; k += .0025){
				//i = a, k = b
				if(this.getInSet(i, k)){
					this.plot(i, k);
				}else{
					if(counter > 900){
						counter = 900;
					}
					counter /= 2;
					float hue = 0.7f + (float) this.counter / 200f;
					mc.setInkColor(Color.getHSBColor(hue, 1f, 1f));
					this.plot(i, k);
					mc.setInkColor(Color.BLACK);
				}
			}
		}

	}
	
	public void drawCoolSet(){
		for(double i = -2; i <= -1.5; i += .0005){
			for(double k = -.045; k <= -.025; k += .00002){
				//i = a, k = b
				if(this.getInSet(i, k)){
					this.plotAtCoolShip(i, k);
				}else{
					if(counter > 900){
						counter = 900;
					}
					counter /= 2;
					float hue = 0.7f + (float) this.counter / 200f;
					mc.setInkColor(Color.getHSBColor(hue, 1f, 1f));
					this.plotAtCoolShip(i, k);
					mc.setInkColor(Color.BLACK);
				}
			}
		}

	}
	
	public void changeMax(int m){
		this.max = m;
	}
	public void erase(){
		mc.erase();
	}
}
