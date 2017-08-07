package com.attilax.facedetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {

    public static void main(String[] args) {
    	  System.load( "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll" );
      //  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\nRunning FaceDetector");

        String path ="";
        		//FaceDetector.class.getResource("haarcascade_frontalface_alt.xml").getPath();
        //meitu syosyo zosh use jeig d ..  zaidren d blog shwa yao use haarcascades hamyar d ..
        path="C:\\progrm\\opencv\\sources\\data\\haarcascades_GPU\\haarcascade_frontalface_alt.xml";
		CascadeClassifier faceDetector = new CascadeClassifier(path);
        String img =  "C:\\0img\\a.jpg";
		Mat image = Highgui
                .imread(img);

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

        for (Rect rect : faceDetections.toArray()) {
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }

        String filename = "C:\\0img\\ouput_faceDetect.png";
        System.out.println(String.format("Writing %s", filename));
        Highgui.imwrite(filename, image);
    }
} 