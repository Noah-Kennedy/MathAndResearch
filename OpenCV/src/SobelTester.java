import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class SobelTester {
	public static void main(String[] args) throws IOException {
		System.load("C:/Users/noah/Desktop/opencv/build/java/x64/opencv_java320.dll");
		BufferedImage image = ImageIO.read(new File("bird-01.jpg"));
		Mat mat = Utilities.matify(image);
		//Mat orig = Utilities.matify(image);

		//Imgproc.GaussianBlur(mat, mat, new Size(51,51), 7, 7);
		Imgproc.blur(mat, mat, new Size(51,51));
		//Imgproc.ga

		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
		//Imgproc.Sobel(mat, mat, -1, 1, 1, 5, 1, 1);


		File f = new File("test.jpg");
		BufferedImage fin = null;
		fin = Utilities.matToBufferedImage(mat, fin);
		Utilities.displayImage(fin);
		ImageIO.write(fin, "jpg", f);
	}
}
