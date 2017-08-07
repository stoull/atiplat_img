package aa;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.imgx;
import com.attilax.img.util.OpencvUtil;
import com.attilax.json.AtiJson;

public class findContoursTest {

	public static void main(String[] args) {
		OpencvUtil.ini();
		String filename = "D:\\0clr\\slt2_close_20knlSize.jpg";
		BufferedImage bi = imgx.toImg(filename);
		Mat src = OpencvUtil.imread(filename);
		// src2 cant be null
		src=OpencvUtil.toBinImg(src);
		Mat src2 = OpencvUtil.to8uc1Colormode(src);
		new Mat(src.rows(), src.cols(), CvType.CV_8UC1);

		// find the contours
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(src2, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
		System.out.println(contours.size());
		for (MatOfPoint matOfPoint : contours) {
			// System.out.println(AtiJson.toJson(matOfPoint));
			List<Point> lst = matOfPoint.toList();
			if (lst.size() > 100) {

				System.out.println(matOfPoint);

				for (Point point : lst) {
					bi.setRGB((int) point.x, (int) point.y, Color.blue.getRGB());
				}

			}

			// System.out.println(lst.size());
			// if(matOfPoint.rows()>5 && matOfPoint.cols()>5)
			// System.out.println(matOfPoint);
		}
		imgx.save_overwrite(bi, "D:\\0clr\\slt2_close_20knlSize_findcontours.jpg");
		System.out.println("--f");
	}
}
