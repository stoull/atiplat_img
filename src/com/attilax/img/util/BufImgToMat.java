package com.attilax.img.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.imgx;

public class BufImgToMat {
	
	
	public static void main(String[] args) {
		String string =   "C:\\progrm\\opencv\\build\\java\\x64\\opencv_java2413.dll";
		System.load(string);
		
		
		String f = "C:\\000oil\\亚当与上帝.jpg";
	 
		BufferedImage img = imgx.toImg(f);
		Mat dst   =new BufImgToMat(img, BufferedImage.TYPE_BYTE_GRAY, CvType. CV_8UC3).getMat();
	//	Imgproc.convertMaps(map1, map2, dstmap1, dstmap2, dstmap1type);
	    
		System.out.println(dst.cols());
	}
        
        BufferedImage originalBufferedImage;
        int itype;
        int mtype;

        /**
         * 
         * @param image
         * @param imgType bufferedImage的类型 如 BufferedImage.TYPE_3BYTE_BGR
         * @param matType 转换成mat的type 如 CvType.CV_8UC3
         */
        public BufImgToMat(BufferedImage image, int imgType, int matType) {
                originalBufferedImage = image;
                itype = imgType;
                mtype = matType;
        }

        public Mat getMat() {
        //	new ImgSearch().ini();
                if (originalBufferedImage == null) {
                        throw new IllegalArgumentException("original == null");
                }

                // Don't convert if it already has correct type
                if (originalBufferedImage.getType() != itype) {

                        // Create a buffered image
                        BufferedImage image = new BufferedImage(originalBufferedImage.getWidth(),
                                        originalBufferedImage.getHeight(), itype);

                        // Draw the image onto the new buffer
                        Graphics2D g = image.createGraphics();
                        try {
                                g.setComposite(AlphaComposite.Src);
                                g.drawImage(originalBufferedImage, 0, 0, null);
                        } finally {
                                g.dispose();
                        }
                }
                DataBuffer dbf=   originalBufferedImage.getRaster().getDataBuffer();
              
                DataBufferByte dbf_byte = (DataBufferByte)dbf;
				byte[] pixels = dbf_byte.getData();
                Mat mat = Mat.eye(originalBufferedImage.getHeight(), originalBufferedImage.getWidth(), mtype);
                mat.put(0, 0, pixels);
                return mat;
        }
}
