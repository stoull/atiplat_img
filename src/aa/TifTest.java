package aa;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.attilax.io.filexEx;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;

public class TifTest {
	
	public  String ConvertTif(String tiffile,String jpg) { // 生成不重复的文件名
		String jpgname = tiffile.replace(".tif", ".jpg");
		String alternateName = jpgname;
//		int i = 0;
//		while (file_exists(alternateName)) {
//			alternateName = i + jpgname;
//			i++;
//		}
		// now alternateName would be final file name to be saved. and we should
		// return that
		try {
			RenderedOp src = JAI.create("fileload", tiffile);
			OutputStream os = new FileOutputStream(jpg);
			JPEGEncodeParam param2 = new JPEGEncodeParam();
			ImageEncoder enc2 = ImageCodec.createImageEncoder("JPEG", os, param2);
			enc2.encode(src);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alternateName;
	}
	
	private boolean file_exists(String alternateName) {
		// TODO Auto-generated method stub
		return new File(alternateName).exists();
	}
	public static void main(String[] args) throws IOException {
		String f="D:\\0workspace\\atiplat_img\\figerprint\\images\\candidate1.tif";
		String jpg=new TifTest().ConvertTif(f,filexEx.addTimestampNSuffix(f, ""));
		System.out.println(jpg);
		BufferedImage bimg = ImageIO.read(new File(f));
		System.out.println(bimg);

	

 }

}
