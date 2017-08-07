
package aa;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.util.OpencvUtil;
import com.attilax.io.filex;
import com.attilax.util.mainThreadEnd;

public class MORPH_CLOSE_test {
	
	public static void main(String[] args) {
		 OpencvUtil.ini();
		String f = "D:\\0clr\\bin.jpg";
		int close_kenelSize = 20;
		Mat morph_closeED = OpencvUtil.morph_close(f, close_kenelSize);
		Highgui.imwrite(filex.addSuffix(f, "" + filex.getUUidName() + "morph_closeED"), morph_closeED);

	 
       System.out.println("--f");
	}

	private static Mat morph_close(String f) {
		int kenelSize =20;
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(kenelSize, kenelSize));  
        Mat src=OpencvUtil.imread(f);
        Mat dst = new Mat(src.rows(), src.cols(), CvType.CV_8UC3);
	//	Imgproc.dilate(src, dst, element);  	
        Imgproc.morphologyEx(src, dst, Imgproc.MORPH_CLOSE, element);
		return dst;
	}

}
