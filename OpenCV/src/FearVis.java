import static org.opencv.core.Core.FILLED;
import static org.opencv.imgproc.Imgproc.RETR_LIST;
import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.drawContours;
import static org.opencv.imgproc.Imgproc.findContours;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class FearVis {
	static double width;
	static double height;
	public static final double heightOfTargetInFeet = 5.0/12; 
	public static final double cameraHeight = 7.0/12;
	static final double bottomHeight = 11.0 / 12; //TODO
	public static final double cameraAngle = 15;

	static double middleX;
	static double middleY;
	public static double cameraFOVWidthInDegrees = 43.84;
	public static double cameraFOVHeightInDegrees = 24.66;
	public static double degreesPerPixelWidth;
	public static double degreesPerPixelHeight;
	static Point middlePoint;
	
	static ArrayList<Double> distances = new ArrayList<Double>();
	static ArrayList<Double> horizontalAngles = new ArrayList<Double>();
	static ArrayList<Double> verticalAngles = new ArrayList<Double>();
	static ArrayList<Double> aspectRatios = new ArrayList<Double>();
	static ArrayList<Double> solidities = new ArrayList<Double>();


	public static void main(String[] args) throws IOException {
		//set up some important constants, like the center pixel of the image
		


		System.load("C:/Users/noah/Desktop/opencv/build/java/x64/opencv_java320.dll");
		BufferedImage image = ImageIO.read(new File("Screenshot (6).jpg"));


		Mat processed = new Mat();
		Mat frame = Utilities.matify(image);
		width = frame.width();
		height = frame.height();
		middleX = (width + 1) / 2;
		middleY = (height + 1) / 2;
		degreesPerPixelWidth = cameraFOVWidthInDegrees / width;
		degreesPerPixelHeight = cameraFOVHeightInDegrees / height;
		middlePoint = new Point(middleX,middleY);
		/*
		 * We are going to keep the frame mat the same until the end when we add
		 * the markers and filtered contours. We will run all of our processing
		 * algorithms on the processed mat. The processed mat will usually serve
		 * as both a destination and source mat. We will first apply a blur to
		 * smooth the image out and remove false positives. Next we will convert
		 * it from BGR to HSV. We will work in HSV because it filters better in
		 * adverse light conditions and at longer distances. We will try and
		 * avoid having large minimum area filters because they impose
		 * substantial limits on our range Next we will filter out all of the
		 * pixels not in our HSV range. Next we will find the contours in our
		 * filtered image. Next we will apply some additional filtering
		 * techniques to remove false positives. Finally we will collect basic
		 * data on the image and draw the filtered contours and markers onto our
		 * output frame.
		 */

		// stop this madness if the frame is empty
		if (frame.empty())
			return;

		// blurs the image to remove false positives
		Imgproc.GaussianBlur(frame, processed, new Size(17, 17), 3);
		//processed = Utilities.matify(image);

		// we are going to use HSV, not BGR for better filtration
		// convert BGR to HSV
		Imgproc.cvtColor(processed, processed, Imgproc.COLOR_BGR2HSV, 0);

		// create scalars to hold high and low thresholds if using BGR
		// shouldn't be using these values because HSV is better
		/*
		 * Scalar lowRange = new Scalar(RobotMap.lowBlueValue,
		 * RobotMap.lowGreenValue, RobotMap.lowRedValue); Scalar highRange = new
		 * Scalar(RobotMap.highBlueValue, RobotMap.highGreenValue,
		 * RobotMap.highRedValue);
		 */

		// create scalars if using HSV
		// should be using these because HSV is best
		Scalar lowRange = new Scalar(55, 230, 0);
		
		//Scalar lowRange = new Scalar(55, 0, 0);

		Scalar highRange = new Scalar(80, 255, 255);
		
		//Scalar highRange = new Scalar(70,255,255);

		// removes everything not in our filter range
		Core.inRange(processed, lowRange, highRange, processed);

		// morphologies, remove false positives and negatives

		// opening, removes false positives
		Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_OPEN,
				Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(9, 9)));

		//closing, removes false negatives
		/*Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_CLOSE,
				Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(9, 9)));*/

		// create an arraylist to hold the unfiltered contours
		ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();

		// find the contours in our image
		Mat m = new Mat();
		findContours(processed, contours, m, RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

		// list of filtered contours
		ArrayList<MatOfPoint> filteredContours = new ArrayList<MatOfPoint>();

		// list of filtered contours as rect objects
		ArrayList<Rect> rects = new ArrayList<Rect>();

		// put our contours into rectangle objects if they pass our conditions
		for (MatOfPoint contour : contours) {
			// bounding rect objects are rectangles whose bounderies encompass
			// all of the contour
			Rect boundingRect = boundingRect(contour);
			// check to see if we are a tallish rectangle with a largish area
			if (
					boundingRect.height > boundingRect.width
					&& FearVis.getPassesContourToRectRatio(contour, boundingRect)
					&& getPassesAspectRatioTest(boundingRect)
					) {

				// add the contours and bounding rects to our filtered lists
				filteredContours.add(contour);
				rects.add(boundingRect);

				//my own distance finding
				//uses angles and geometry, not fudge factors and approximations
				//has external documentation
				double rads = Math.toRadians(findVerticalAngleToPoint(boundingRect.tl()) - cameraAngle);
				double distanceToTarget = (heightOfTargetInFeet + bottomHeight - cameraHeight) / (Math.tan(rads));

				
				distances.add(distanceToTarget);
				
				/*angle finding, uses linear approximation method
				because it's close enough with our camera and
				add them to a list of length numTargets*/
				
				//horizontal
				double angle = findHorizontalAngleToPoint(center(boundingRect));
				horizontalAngles.add(angle);
				
				//vertical
				angle = findVerticalAngleToPoint(center(boundingRect));
				verticalAngles.add(angle);
				aspectRatios.add((double) ((double) boundingRect.width) / boundingRect.height);
				solidities.add(getSolidity(contour, boundingRect));
			}

		}
		

		//draw our filtered contours
		drawContours(frame, filteredContours, -1, new Scalar(0, 0xFF, 0), FILLED);

		//figure out how many targets there are
		
		//draw marker at center of all rects
		if(rects.size() > 0)
			Imgproc.drawMarker(frame, center(rects), new Scalar(0xFF, 0, 0xFF));
		
		//draw markers to show info on each rect
		for (Rect rect : rects) {
			Imgproc.drawMarker(frame, center(rect), new Scalar(0, 0, 0xFF));
			Imgproc.drawMarker(frame, rect.br(), new Scalar(0xFF, 0, 0));
			Imgproc.drawMarker(frame, rect.tl(), new Scalar(0xFF, 0, 0));
		}
		
		Imgproc.drawMarker(frame, middlePoint, new Scalar(0xFF,0xFF,0xFF));

		

		
		//draw a point in the middle of the screen

		
		BufferedImage fin = null;
		BufferedImage proc = null;

		fin = Utilities.matToBufferedImage(frame, fin);
		Utilities.displayImage(fin);
		
		proc = Utilities.matToBufferedImage(processed, proc);
		Utilities.displayImage(proc);
		System.out.println("Num Targets: " + rects.size());
		System.out.println("Distances " + distances);
		System.out.println("vertical angles " + verticalAngles);
		System.out.println("horizontal angles " + horizontalAngles);
		System.out.println("Aspect Ratios: " + aspectRatios);
		System.out.println("Solidities: " + solidities);
	}
	

	/**
	 * Takes in two opencv Points and outputs the opencv point at the midpoint
	 * 
	 * @param p1
	 * @param p2
	 * @return the midpoint of the two points
	 */
	private static Point midpoint(Point p1, Point p2) {
		double x = p1.x + p2.x;
		x /= 2.0;
		double y = p1.y + p2.y;
		y /= 2.0;
		return new Point(x, y);
	}

	/**
	 * Takes in an opencv rect object and finds its center
	 * 
	 * @param r
	 * @return the center of the rect
	 */
	private static Point center(Rect r) {
		return midpoint(r.br(), r.tl());
	}
	
	/**
	 * Averages all of the top left and bottom right corners together to find the center point
	 * @param rects a list of rect objects
	 * @return the point at the center of the list
	 */
	private static Point center(ArrayList<Rect> rects){
		double x = 0;
		double y = 0;
		for(Rect r : rects){
			x += center(r).x;
			y += center(r).y;
		}
		
		x /= ((double) rects.size());
		y /= ((double) rects.size());
		return new Point(x,y);
	}
	
	private static boolean getPassesContourToRectRatio(MatOfPoint c, Rect r){
		return Imgproc.contourArea(c) / r.area() >= .65
				&& Imgproc.contourArea(c) / r.area() <= 1;
	}
	
	private static boolean getPassesAspectRatioTest(Rect r){
		//return true;
		return getAspectRatio(r) >= .3 && getAspectRatio(r) <= .6;
	}
	
	private static double getAspectRatio(Rect r){
		return (double) ((double) r.width) / r.height;
	}
	
	/**
	 * Find the horizontal angle from the normal
	 * @param p the point of interest
	 * @return the angle from the normal to the point horizontally
	 */
	private static double findHorizontalAngleToPoint(Point p){
		return (p.x - middleX) * degreesPerPixelWidth;
	}
	
	/**
	 * Find the vertical angle from the normal
	 * @param p the point of interest
	 * @return the angle from the normal to the point vertically
	 */
	private static double findVerticalAngleToPoint(Point p){
		return -(p.y - middleY) * degreesPerPixelHeight;
	}
	
	private static double getSolidity(MatOfPoint c, Rect r) {
		return Imgproc.contourArea(c) / r.area();
	}
}
