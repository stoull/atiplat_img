package aa;

import java.awt.image.BufferedImage;

import com.attilax.img.imgx;
import com.attilax.io.filex;
import com.attilax.io.filexEx;

public class invertTest {
	
	public static void main(String[] args) {
		String f = "D:\\0clr\\close.jpg";
		BufferedImage closedBufimg=imgx.toImg(f);
		new com.jhlabs.image.MaskFilter()
		BufferedImage	Invertcopy = new com.jhlabs.image.InvertFilter().filter(closedBufimg, null);
		BufferedImage int_bin=new imgx().binaryImage(Invertcopy);
		String suf_ivt = filex.getUUidName()+"invert";
		imgx.save_overwrite(Invertcopy, filex.addSuffix(f, suf_ivt));
		
		imgx.save_overwrite(int_bin, filexEx.addTimestampNSuffix(f,  "invent_bin"));
	}

}
