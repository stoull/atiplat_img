package com.attilax.cca;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.attilax.img.HSV;
import com.attilax.img.HsvRange;
import com.attilax.img.imgUtil;
import com.attilax.img.imgx;
import com.attilax.img.other.ColorUtil;
import com.attilax.io.filex;
import com.google.common.collect.Lists;

public class CCAati {
	
	List<Pix> waitPixs=Lists.newArrayList();
	List<Area> areas=Lists.newArrayList();
	public CCAati(String img) {
		// TODO Auto-generated constructor stub
	}

	public CCAati(BufferedImage mBufferedImage) {
		this.mBufferedImage=mBufferedImage;
	}
	   BufferedImage mBufferedImage;
	public static void main(String[] args) throws IOException {
		Color c=new Color(250, 250, 250);
		HSV hsv=ColorUtil.rgb2hsv(c);
		System.out.println(hsv);
		HsvRange hr=new HsvRange();
		hr.hMin=170;
		hr.hMax=220;
		hr.sMin=0.1;
		hr.sMax=0.2;hr.vMin=0.7;hr.vMax=0.9;
		String img_str="C:\\00\\v.jpg";
		BufferedImage img = imgx.toImg(img_str);
		List<Pix> ps=imgUtil.findPxsByColor(hr,img);
		
		imgUtil.delBkgrd(img,Color.WHITE,ps);
		
		imgx.save_overwrite(img, filex.addSuffix(img_str, "delgrd"));
//		   String img = "c:\\00\\b.jpg";
//		   BufferedImage mBufferedImage = ImageIO.read(new File(img));
//		   CCAati ccAati = new CCAati(mBufferedImage);
//		ccAati.ccaAnaly();
//		System.out.println(ccAati.areas.size());
		   
	}

	private void ccaAnaly() {
		
		waitPixs=getWaitPixs();
		while(waitPixs.size()>0)
		{
			Pix px=waitPixs.get(0);
			Area a=getAreaByPix(px);
			Minus(waitPixs,a);
			areas.add(a);
		}
	
		
		
		 
	}

	private void Minus(List<Pix> waitPixs2, Area a) {
		List<Pix> wait2del=Lists.newArrayList();
		for (Pix pix : waitPixs2) {
			if(a.contain(pix))
				wait2del.add(pix);
		}
		waitPixs2.removeAll(wait2del);
	}

	private Area getAreaByPix(Pix px) {
	 List<Pix> pxs  =   new SeedFillAlgo().getAreaByPix(px,mBufferedImage);
		return new Area(pxs);
		 
	}

	private List<Pix> getWaitPixs() {
		List<Pix> waitPixs=Lists.newArrayList();
		 for(int w=0;w<this.mBufferedImage.getWidth();w++)
			 for(int h=0;h<this.mBufferedImage.getHeight();h++)
			 {
				 Point  pt=new Point(w, h);
				 Pix px=new Pix(pt);
				// waitPixs.pt=pt;
				 int rgb = mBufferedImage.getRGB(w, h);
				 Color clr=new Color(rgb);
				px.color=clr;
				waitPixs.add(px);
			 }
		 return waitPixs;
	}
	

}
