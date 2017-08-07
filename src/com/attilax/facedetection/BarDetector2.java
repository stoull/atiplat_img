package com.attilax.facedetection;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

import com.attilax.img.imgx;
import com.attilax.img.util.OpencvUtil;
import com.attilax.io.filex;

public class BarDetector2 {

    public static void main(String[] args) {
    	OpencvUtil.ini();
      //  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\nRunning FaceDetector");
        String bg="D:\\0bar\\t.jpg";
        String t="D:\\0bar\\t.jpg";
    	BufferedImage src=imgx.toImg(bg);
		//bin img
		BufferedImage bin=	new	imgx().binaryImage(src);		
	//	bin=	new	imgx().binaryImage(bin);
	//	bin= new com.jhlabs.image.ThresholdFilter().filter(ranged_bufimg, null);
		String binfile = filex.addSuffix(bg, "" + filex.getUUidName() + " bined");
		imgx.save_overwrite(bin,binfile);
	
//		if("1".equals("1"))
//				return Lists.newArrayList();
		//----------------ivt 
		BufferedImage	Invertcopy = new com.jhlabs.image.InvertFilter().filter(bin, null);
		String suf_ivt = filex.getUUidName()+"invert";
		imgx.save_overwrite(Invertcopy, filex.addSuffix(bg, suf_ivt));
		
		
		int close_kenelSize=8;
		//note muse use filemode..if drktl  bufimg mode ,then cant close correct,,,bsyso veishen..
		Mat morph_closeED = OpencvUtil.morph_close(Invertcopy, close_kenelSize);
		Highgui.imwrite(filex.addSuffix(bg, "" + filex.getUUidName() + "clsd"), morph_closeED);

        String filename = "C:\\0img\\ouput_faceDetect.png";
        System.out.println(String.format("Writing %s", filename));
   //     Highgui.imwrite(filename, image);
    }
} 