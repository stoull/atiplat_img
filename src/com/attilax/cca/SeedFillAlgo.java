package com.attilax.cca;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.attilax.img.imgUtil;
import com.attilax.img.imgx;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;
import com.google.common.collect.Lists;

public class SeedFillAlgo {
	BufferedImage img;
	private Logger logger =new EmptyLogger();
			//LoggerFactory.getLogger(getClass());
	/**
	 * @param img2
	 */
	public SeedFillAlgo(BufferedImage img2) {
		img = img2;
	}

	public static void main(String[] args) throws FileExistEx {
		BufferedImage img = imgx.toImg("C:\\000money\\b.jpg");

		Pix px = new Pix(new Point(0, 0));
		List<Pix> area = new SeedFillAlgo(img).getAreaByPix(px, img, 30);
		
		for (Pix pix : area) {
			int c=new Color(0,0,0).getRGB();
			img.setRGB(pix.point.x, pix.point.y, c);
		}
		imgx.save(img, "C:\\000money\\c_blk+"+filex.getUUidName()+".jpg");
		System.out.println(area.size());
	}

	public List<Pix> getAreaByPix(Pix px, BufferedImage mBufferedImage, int threotNum) {
		List<Pix> areaPixs = Lists.newArrayList();
		areaPixs.add(px);
		List<Pix> waitPixs = Lists.newArrayList();
		waitPixs.add(px);
		List<Pix> processedPixs = Lists.newArrayList();
		while (waitPixs.size() > 0) {
		//	System.out.println(AtiJson.toJson(waitPixs));
			Pix top1PxFrmWaitPxsList = waitPixs.get(0);
			logger.info(" now pix:"+AtiJson.toJson(top1PxFrmWaitPxsList));
			List<Pix> neibsPixs = getneibsPixs(top1PxFrmWaitPxsList);

			//neibsPixs.removeAll(processedPixs);
			// List<Pix>
			//---------neibsPixs minus  processedPixs
			neibsPixs_minusProcessedPixs(neibsPixs, processedPixs);
			List<Pix> simlerNeibPixs = get_simlerNeibPixs(neibsPixs, top1PxFrmWaitPxsList, threotNum);
			logger.info(" simlerNeibPixs pix:"+AtiJson.toJson(simlerNeibPixs));
			
			//---------simlerNeibPixs into waitPixs
			 addPixsList2anotherPixsList(simlerNeibPixs,waitPixs);			
			waitPixs.remove(0);
			logger.info(" waitPixs pix:"+AtiJson.toJson(waitPixs));
			logger.info(" waitPixs_cont:"+ waitPixs.size());
			
	    	//------------	areaPixs.addAll(simlerNeibPixs);		
			addPixsList2anotherPixsList(simlerNeibPixs, areaPixs);
			
			logger.info(" areaPixs pix:"+AtiJson.toJson(areaPixs));
		
			
			//	processedPixs.add(next);
			addPix2anotherPixsList_P2l(top1PxFrmWaitPxsList, processedPixs);	
			logger.info(" processedPixs pix:"+AtiJson.toJson(processedPixs));
			logger.info(" processedPixs_cont:"+processedPixs.size());
		}

		return areaPixs;
	}

	/**
	attilax    2017年1月11日  下午11:09:15
	 * @param next
	 * @param processedPixs
	 */
	private void addPix2anotherPixsList_P2l(Pix pix2, List<Pix> processedPixs) {
		 
			if( !PixsContain(pix2,processedPixs)  )
			{
				processedPixs.add(pix2);
			}
			
	 
		
	}

	/**
	attilax    2017年1月11日  下午10:32:23
	 * @param simlerNeibPixs
	 * @param pxs
	 */
	private void addPixsList2anotherPixsList(List<Pix> simlerNeibPixs, List<Pix> pxs) {
		for (Pix pix : simlerNeibPixs) {
			if( !PixsContain(pix,pxs)  )
			{
				pxs.add(pix);
			}
			
		}
	//	waitPixs.addAll(simlerNeibPixs);	
		
	}

	/**
	attilax    2017年1月11日  下午10:34:16
	 * @param pix
	 * @param pxs 
	 * @return
	 */
	private boolean PixsContain(Pix pix, List<Pix> pxs) {
		for (Pix pix2 : pxs) {
			if( pixEq(pix,pix2))
				return true;
		}
		return false;
	}

	/**
	attilax    2017年1月11日  下午10:35:02
	 * @param pix
	 * @param pix2
	 * @return
	 */
	private boolean pixEq(Pix pix, Pix pix2) {
		 
		return new Integer(pix.point.x ).equals( pix2.point.x)   &&  new Integer(pix.point.y ).equals( pix2.point.y);
	}

	private List<Pix> neibsPixs_minusProcessedPixs(List<Pix> neibsPixs, List<Pix> processedPixs) {
		List<Pix> wait2del=Lists.newArrayList();
		for (Pix pix : neibsPixs) {
			for (Pix pix2 : processedPixs) {
				if (pix.point.x == pix2.point.x && pix.point.y == pix2.point.y) {
					wait2del.add(pix);
					
				}
			}
		}
		neibsPixs.removeAll(wait2del);
		return neibsPixs;
		// neibsPixs.removeAll(processedPixs);
	}

	private List<Pix> get_simlerNeibPixs(List<Pix> neibsPixs, Pix tmpltPix, int threotNum) {
		List<Pix> li = Lists.newArrayList();
		for (Pix pix : neibsPixs) {
			// String curColor=img.getRGB(x, y)

			try {
				Color curClr = imgUtil.color(img, pix.point);
				Color tmpltPixClr = imgUtil.color(img, tmpltPix.point);
				if (imgUtil.isSimilar(curClr, tmpltPixClr, threotNum))
					li.add(pix);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("--ArrayIndexOutOfBoundsException:" + pix.point.toString());
			}

		}
		return li;

	}

	private List<Pix> getneibsPixs(Pix next) {
		Point p = next.point;
		List<Pix> li = Lists.newArrayList();

		if (p.y - 1 >= 0) {
			Point Point_top = new Point(p.x, p.y - 1);
			Pix top = new Pix(Point_top);
			li.add(top);
		}

		if (p.x - 1 >= 0) {
			Point Point_left = new Point(p.x - 1, p.y);
			Pix lft = new Pix(Point_left);
			li.add(lft);
		}

		Point Point_botton = new Point(p.x, p.y + 1);
		Pix btm = new Pix(Point_botton);
		li.add(btm);

		Point Point_right = new Point(p.x + 1, p.y);
		Pix rit = new Pix(Point_right);
		li.add(rit);
		return li;

	}

}
