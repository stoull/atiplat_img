package android.widget;

import com.attilax.img.util.OpencvUtil;

public class Toast {

	private String msg;

	public static Toast makeText(Object opencvUtil, String string, int i) {
		// TODO Auto-generated method stub
	 
		Toast toast = new Toast();
		toast.msg=string;
		return toast;
	}

	public void show() {
		 
	System.out.println(this.msg);
	}

}
