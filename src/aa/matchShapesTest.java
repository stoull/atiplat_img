package aa;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.util.OpencvUtil;

public class matchShapesTest {
	private static final int CV_LOAD_IMAGE_GRAYSCALE = 0;

	public static void main(String[] args) {
		
		OpencvUtil.ini();  
		//LOAD_IMAGE_GRAYSCALE
		Mat contour1=Highgui.imread("D:\\0bar\\a_0205_144139_338clsd.jpg",CV_LOAD_IMAGE_GRAYSCALE );
				//OpencvUtil.imread("D:\\0bar\\a_0205_144139_338clsd.jpg");
		String t = "D:\\0bar\\t_c.jpg";
		Mat contour2=Highgui.imread(t,CV_LOAD_IMAGE_GRAYSCALE );
				//OpencvUtil.imread(t);;
		//contour1_32f=
		contour1.convertTo(contour1, CvType.CV_32FC2);
		contour2.convertTo(contour2, CvType.CV_32FC2);
		//contour1.channels()==3
		for(int i=0;i<7;i++)
		{
			 System.out.println("vect:"+i +" ,chek:"+contour1.checkVector(i) );
		}

		double rzt=Imgproc.matchShapes(contour1, contour2, Imgproc.CV_CONTOURS_MATCH_I1, 0);
		System.out.println("rzt:"+rzt);
	}

}
