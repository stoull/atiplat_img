package aa;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.CoreImg;
import com.attilax.img.HSV;
 
import com.attilax.img.imgx;
import com.attilax.img.util.OpencvUtil;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.io.filexEx;
import com.google.common.collect.Lists;


/**
 * aa.findColorAreasCls
 * @author Administrator
 *
 */
public class findColorAreasCls {

	public static void main(String[] args) throws FileExistEx {
		OpencvUtil.ini();
		findColorAreasCls fca = new findColorAreasCls();
		String f = "D:\\0clr\\b.jpg";
		BufferedImage bi = imgx.toImg(f);
		int lo_h = 340;
		HSV low = new HSV(lo_h, 0.25f, (float) 0.58);
		HSV hi = new HSV(360, 0.75f, (float) 0.99);
		int close_kenelSize = 20;
		List<MatOfPoint> contours = fca.findColorAreas(f, low, hi,close_kenelSize);
		for (MatOfPoint matOfPoint : contours) {
		 
			
			OpencvUtil.setRangeEdgeColor(bi,matOfPoint,Color.blue.getRGB());
		//	Core.seti
		//	Highgui.set
		//	SetImageROI
		}
		imgx.save_overwrite(bi, filex.addSuffix(f, "" + filex.getUUidName() + "selected"));
		Mat src=OpencvUtil.imread(f);
		Imgproc.drawContours(src, contours,-1, new Scalar(255,255,255),-1);//-1sh  fill innner...or border
		Highgui.imwrite(filex.addSuffix(f, "" + filex.getUUidName() + "jwoKonto"), src);
		
		// t();

		System.out.println("--f");

	}

	private List<MatOfPoint> findColorAreas(String f, HSV low, HSV hi, int close_kenelSize) throws FileExistEx {
		BufferedImage src = imgx.toImg(f);
		// hsv lit>>dark 345,25,99>>343 73 80>>340 54 99

		BufferedImage ranged_bufimg = CoreImg.inRange(src, low, hi);
		//dbg out for ranged_bufimg
		String outFile_ranged = filex.addSuffix(f, "" + filex.getUUidName() + "ranged");
		imgx.save(ranged_bufimg, outFile_ranged);
		
		//bin img
		BufferedImage bin=	new	imgx().binaryImage(ranged_bufimg);		
	//	bin=	new	imgx().binaryImage(bin);
	//	bin= new com.jhlabs.image.ThresholdFilter().filter(ranged_bufimg, null);
		String binfile = filex.addSuffix(f, "" + filex.getUUidName() + "ranged_bined");
		imgx.save_overwrite(bin,binfile);
	
//		if("1".equals("1"))
//				return Lists.newArrayList();
		
		//note muse use filemode..if drktl  bufimg mode ,then cant close correct,,,bsyso veishen..
		Mat morph_closeED = OpencvUtil.morph_close(binfile, close_kenelSize);
		Highgui.imwrite(filex.addSuffix(f, "" + filex.getUUidName() + "clsd"), morph_closeED);
		
		
		//bin agein
		BufferedImage morph_closeED_bufimg=OpencvUtil.mat2bufImg(morph_closeED);
		BufferedImage closeED_bin=	new	imgx().binaryImage(morph_closeED_bufimg);	
		String maskfile = filexEx.addTimestampNSuffix( f, "close_bin");
		imgx.save_overwrite(closeED_bin, maskfile);
		
		
			
		
		 BufferedImage only_txt =imgx. mask(f, maskfile);
		 imgx.save_overwrite(only_txt, filexEx.addTimestampNSuffix( f, "only_txt"));
//		Mat close_bin_mat=OpencvUtil.bufImg2mat(closeED_bin);
//	Mat txt_mask_rzt=	OpencvUtil.mask(src,morph_closeED);
//	Highgui.imwrite(filex.addSuffix(f, "" + filex.getUUidName() + "mask_txt"), txt_mask_rzt);
	
	
	
	// invert
//			BufferedImage closedBufimg=OpencvUtil.mat2bufImg(morph_closeED);
//					BufferedImage	Invertcopy = new com.jhlabs.image.InvertFilter().filter(closedBufimg, null);
//					String suf_ivt = filex.getUUidName()+"invert";
//					imgx.save_overwrite(Invertcopy, filex.addSuffix(f, suf_ivt));
//	Mat bg_mask_rzt=	OpencvUtil.mask(src,Invertcopy);
//	Highgui.imwrite(filex.addSuffix(f, "" + filex.getUUidName() + "bg_mask_rzt"), bg_mask_rzt);
	
		List<MatOfPoint> li = OpencvUtil.findContours(morph_closeED);
		return li;
	}

	private static void t() {
		OpencvUtil.ini();
		String f = "D:\\0clr\\b.jpg";
		Mat src = OpencvUtil.imread(f);
		// hsv lit>>dark 345,25,99>>343 73 80>>340 54 99
		Mat img_hsv = new Mat(src.rows(), src.cols(), CvType.CV_8UC3);
		Imgproc.cvtColor(src, img_hsv, Imgproc.COLOR_RGB2HSV);
		Scalar lowerb = new Scalar(300, 0, 0); // 120 , 163
		Scalar upperb = new Scalar(350, 255, 255);
		Mat dst = new Mat(src.rows(), src.cols(), CvType.CV_8UC3);

		// Scalar hsv_min = new Scalar(0,0,0); Scalar hsv_max = new
		// Scalar(255,255,30);
		// Core.inRange(dst, hsv_min, hsv_max, hsv_mask);
		Core.inRange(img_hsv, lowerb, upperb, dst);

		BufferedImage bi = OpencvUtil.mat2bufImg(dst);
		String outFile = filex.addSuffix(f, "ranged" + filex.getUUidName());
		// imgx.save(bi,outFile );
		Highgui.imwrite(outFile, dst);
		Highgui.imwrite("D:\\0clr\\b_hsv.jpg", img_hsv);
	}

}
