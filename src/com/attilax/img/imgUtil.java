package com.attilax.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import com.attilax.cca.Pix;
import com.attilax.img.other.ColorUtil;
import com.attilax.util.FileUtils;
import com.google.common.collect.Lists;

public class imgUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] b = imgUtil.toByteArr("d:\\t.jpg");
		System.out.println(b.length);

	}

	public static byte[] toByteArr(String string) {
		byte[] b = null;
		try {
			b = FileUtils.toByteArray(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * attilax 2017��1��11�� ����7:26:56
	 * 
	 * @param img
	 * @param point
	 * @return
	 */
	public static Color color(BufferedImage img, Point point) {
		int c_int = img.getRGB(point.x, point.y);
		return new Color(c_int);
	}

	public static boolean isSimilarColor(int cur_color, int select_color_int) {
		if (cur_color == select_color_int)
			return true;
		Color curClr = new Color(cur_color);
		Color select_color = new Color(select_color_int);
		int threotNum = 25;
		
		
		return isSimilar(curClr, select_color, threotNum);
	}

	public static boolean isSimilar(Color curClr, Color select_color, int threotNum) {
		int red_diff = Math.abs(curClr.getRed() - select_color.getRed());
		int green_diff = Math.abs(curClr.getGreen() - select_color.getGreen());
		int blue_diff = Math.abs(curClr.getBlue() - select_color.getBlue());
		
		if (red_diff < threotNum && green_diff < threotNum && blue_diff < threotNum)
			return true;
		return false;
	}

	public static List<Pix> findPxsByColor(HsvRange hr, BufferedImage img) {
		 List<Pix> li=Lists.newArrayList();
		ImgTraver ir=new ImgTraver();
		ir.process_cur_Pix_Point_Fun_Handler=p->{
			if(p.x==209 && p.y==48)
				System.out.println("dbg");
			int clr_i=img.getRGB(p.x, p.y);
			HSV hs=ColorUtil.rgb2hsv(clr_i);
			if(hr.contain(hs))
				li.add(new Pix(p));
		};
		ir.trave_fromTop2down(img); 
 	return  li;
	}

	public static void delBkgrd(BufferedImage img, Color white, List<Pix> savePx) {
		ImgTraver ir=new ImgTraver();
		ir.process_cur_Pix_Point_Fun_Handler=p->{
			 if(!PixUtil.list_contain(savePx,p))
			 {
				 if(white==Color.white)
					 img.setRGB(p.x, p.y,Color.white.getRGB() );
			 }
		};
		ir.trave_fromTop2down(img); 
		//return this;
	}

}
