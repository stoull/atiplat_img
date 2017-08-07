package aa;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.opencv.core.Core;
 
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.attilax.img.imgx;
import com.attilax.img.other.CantFindMatch;
import com.attilax.img.other.TemplateMaching;
import com.attilax.img.util.OpencvUtil;
import com.attilax.io.filex;

public class matchTemplateTest {

	public static void main(String[] args) {
		
		OpencvUtil.ini();
 
		String bigimg = "D:\\0bar\\b2.jpg"; 
 
		String tmp_pic = "D:\\0bar\\t2.jpg";
		
	List<Point> li=	OpencvUtil.matchTemplate(bigimg, tmp_pic, "D:\\0bar", Imgproc.TM_SQDIFF, 3);
	
	BufferedImage tmp_pic_img=imgx.toImg(tmp_pic);
	Rectangle rect=new Rectangle(-1, -1, tmp_pic_img.getWidth(), tmp_pic_img.getHeight());
	imgx.rectangle(bigimg,li,rect,Color.red);
	
	
		System.out.println("ff");
	 

	}

}
