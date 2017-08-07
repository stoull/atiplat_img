/**
 * 
 */
package com.attilax.ocr;

/**
 * @author attilax
 *2016年11月1日 下午9:12:48
 */
public class ocrTest {

	/**
	attilax    2016年11月1日  下午9:12:48
	 * @param args
	 */
	public static void main(String[] args) {
		String jpg="D:\\ati\\dcim_mov22\\IMG_0983.PNG";
		  jpg="D:\\ati\\dcim_mov22\\IMG_0182.PNG";
		
		System.out.println( new OcrX().getTxt("C:\\0workspace\\Tesseract",jpg));

	}

}
