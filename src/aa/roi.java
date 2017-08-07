package aa;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.imgx;
import com.attilax.img.util.OpencvUtil;
import com.attilax.io.filex;

public class roi {
	
	public static void main(String[] args) {
		OpencvUtil.ini();
		String src_img_file="D:\\0clr\\b.jpg";
		String f="D:\\0clr\\close.jpg";
		BufferedImage bi=imgx.toImg(f);
		Mat morph_closeED= OpencvUtil.imread(f);
		List<MatOfPoint> li = OpencvUtil.findContours(morph_closeED);
		for (MatOfPoint matOfPoint : li) {
		//	opencv3计算轮廓的面积-contourArea函数
//			  MatOfPoint2f approxCurve = new MatOfPoint2f();
//			   int contourSize = (int)matOfPoint.total();
//			   MatOfPoint2f new_mat = new MatOfPoint2f( matOfPoint.toArray() );
//			 Imgproc.approxPolyDP(new_mat, approxCurve, contourSize*0.05, true);
			 Rect rct=Imgproc.boundingRect(matOfPoint);
			 Rectangle rect2=OpencvUtil.toRectangle(rct);
			 BufferedImage bi2= new imgx().cutImage_retImg(src_img_file, rect2);
			 imgx.save_overwrite(bi2, filex.addSuffix(src_img_file, filex.getUUidName() + "rect"));
			// boundingRect
		}
		System.out.println("--f");
		
	}

}
