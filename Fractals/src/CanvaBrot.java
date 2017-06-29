import java.awt.Color;
import java.awt.Point;

public class CanvaBrot {
	public static void main(String[] args) throws InterruptedException{
	final int width = 1920, height = 1080;
    final Canvas image = new Canvas("CanvaBrot");
    image.setSize(width, height);
    image.setVisible(true);
    image.setInkColor(Color.BLACK);
	for(int i = 0; i < 10000000; i++) {
	System.out.println(i);
	image.erase();
	int max = i;
    for (int row = 0; row < height; row++) {
    	final int ra = row;
    	Thread t = new Thread(() -> {
    	final int r = ra;
    	//flag = true;
    	//Thread.currentThread().notifyAll();
        for (int col = 0; col < width; col++) {
            double c_re = (col - width/2)*4.0/width;
            double c_im = (r - height/2)*4.0/width;
            double x = 0, y = 0;
            int iterations = 0;
            while (x*x+y*y < 4 && iterations < max) {
                double x_new = x*x-y*y+c_re;
                y = 2*x*y+c_im;
                x = x_new;
                iterations++;
            } 
            if (iterations < max){
            	image.setInkColor(Color.WHITE);
            	image.drawPoint(new Point(col, r));;
            }
            else {
            	image.setInkColor(Color.BLACK);
            	image.drawPoint(new Point(col, r));;
            }
        }
        });
    	t.start();
    }

	}
	}
}
