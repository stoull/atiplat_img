/**
 * 
 */
package com.attilax.ocr;

/**
 * @author attilax
 *2016年11月1日 下午9:55:56
 */
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.attilax.img.imgx;
import com.attilax.io.filex;

public class TestOcr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//输入图片地址  yaobuyao tif ,,sh yyo d effect ..n test by myself...paa
		String path = "d://test//test.bmp";   //D:\ati\dcim_mov22\IMG_0177.PNG
		String jpg="D:\\ati\\dcim_mov22\\IMG_0177.PNG";
        try {   
          //  String valCode = new OCR().setTessPath("C:\\0workspace\\Tesseract").recognizeText(new File(jpg), "png");   
        	
        	
        	String srcimg = "C:\\000money\\0109_215946_000.jpg";
			BufferedImage src=imgx.toImg(srcimg)	;
        	
        	 BufferedImage bin=new imgx().binaryImage(src);
        	 String bin_path = filex.addSuffix(srcimg, "bin");
			imgx.save(bin, bin_path);
        	 
        	 String valCode=new OcrX().getCaptch("C:\\0workspace\\Tesseract", bin_path);
            System.out.println("valCode:"+valCode);   
        } catch (Exception e) {
			e.printStackTrace();
		}    
	}

}
