package aa;

import com.attilax.img.TextureFeatureUtil;

//21  0  15
public class figerPhashTest {
	public static void main(String[] args) {
		 String f = Path.Combine("D:\\0workspace\\atiplat_img\\figerprint\\images", "candidate1.jpg");
		    String f2 = Path.Combine("D:\\0workspace\\atiplat_img\\figerprint\\images", "candidate2.jpg");
		    String f3 = Path.Combine("D:\\0workspace\\atiplat_img\\figerprint\\images", "candidate3.jpg");
		    
		    String prb = Path.Combine("D:\\0workspace\\atiplat_img\\figerprint\\images", "prb.jpg");
			final TextureFeatureUtil p = new TextureFeatureUtil();
			int dis_1 = p.distance_img(prb, f);
			int dis_2 = p.distance_img(prb, f2);
			int dis_3 = p.distance_img(prb, f3);
			System.out.println( dis_1 +"  "+dis_2+"  "+dis_3);
	}

}
