package com.attilax.img;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.highgui.Highgui;
import org.opencv.features2d.*;

public class SfitTest {

	
	/**
	 * ret  
	 * 128
	 	1589
	 * @param args
	 */
	public static void main(String[] args) {
	
	 
		 //opencv_java2413  Core.NATIVE_LIBRARY_NAME
		    System.load( "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll" );
		    Mat test_mat = Highgui.imread("C:\\000sklt\\2.jpg");
		    Mat desc = new Mat();
		    FeatureDetector fd = FeatureDetector.create(FeatureDetector.SIFT);
		    MatOfKeyPoint mkp =new MatOfKeyPoint();
		    fd.detect(test_mat, mkp);
		    DescriptorExtractor de = DescriptorExtractor.create(DescriptorExtractor.SIFT);
		    de.compute(test_mat,mkp,desc );//提取sift特征
		    System.out.println(desc.cols());
		    System.out.println(desc.rows());
		 
		 
		 
	}

}
