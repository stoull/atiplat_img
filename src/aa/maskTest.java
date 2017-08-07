package aa;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import com.attilax.img.ImgTraver_lineScaner;
import com.attilax.img.imgx;
import com.attilax.img.util.OpencvUtil;
import com.attilax.io.filex;
import com.attilax.io.filexEx;

public class maskTest {

	public static void main(String[] args) {
	 
		//t();
		 String f = "D:\\0clr\\a.jpg";
		 String maskFile="D:\\0clr\\msk.jpg";
		 BufferedImage src = mask(f, maskFile);
		 imgx.save_overwrite(src, filexEx.addTimestampNSuffix( f, "b_msk"));
			System.out.println("--f");
		 
	}

	private static BufferedImage mask(String f, String maskFile) {
		BufferedImage mskBufImg=imgx.toImg(maskFile);
		 BufferedImage src=imgx.toImg(f);
		 ImgTraver_lineScaner  trl=new ImgTraver_lineScaner(f);
		 trl.cur_Pix_Point_process_Fun_Handler=p->{
			 int clr=mskBufImg.getRGB(p.x,p.y);
			 if(imgx.isDarkColor(clr,128))
			 {
				 src.setRGB(p.x, p.y, Color.black.getRGB());
			 }
		 };
		 trl.trav();
		return src;
	}

	private static void t() {
		OpencvUtil.ini();
		// mask image
		 Mat img_masked;
		 String f = "D:\\0clr\\b.jpg";
		 Mat src=OpencvUtil.imread(f);
		 
		 Mat mask=OpencvUtil.imread("D:\\0clr\\mask.jpg");
		 
			Mat result = Mat.zeros(src.rows(), src.cols(), CvType.CV_8UC3);
		 src.copyTo(result, mask);
			Highgui.imwrite(filex.addSuffix(f, "" + filex.getUUidName() + "mask_txt"), result);
	}
}
