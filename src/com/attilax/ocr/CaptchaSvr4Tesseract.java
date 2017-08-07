package com.attilax.ocr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aaBaolilo.ImageDemo;

import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.lang.CmdX;

public class CaptchaSvr4Tesseract {
	public static boolean dbg = false;

	public static void main(String[] args) {
		// batch();
		try {
			if (args[0].equals("-dbg"))
				dbg = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		String capt_test = pathx.webAppPath() + "/capt/capt_test.jpg";
		System.out.println(new CaptchaSvr4Tesseract().getCaptch(capt_test));

	}

	private static void batch() {
		// "C:\Program Files (x86)\Tesseract-OCR\tesseract.exe" e:\a.jpg
		// e:\a_ok.jpg -l chi_sim
		String baseName = "a";
		String dir = "c:\\bb";
		File dir2 = new File(dir);
		File[] files = dir2.listFiles();
		for (File file : files) {

			ImageDemo demo = new ImageDemo();
			String bname = filex.getFileName_noExtName(file.getAbsolutePath());
			String exname = filex.getExtName(file.getAbsolutePath());
			String bin_jpg = dir + "\\" + bname + "_bin." + exname;
			try {
				demo.binaryImage(file.getAbsolutePath(), bin_jpg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(getCaptch(bin_jpg));

			// String
			// cmd="\"C:\\Program Files (x86)\\Tesseract-OCR\\tesseract.exe\" e:\\"+baseName+".jpg e:\\"+baseName+"_ok.jpg -psm 8";
			// String ret=CmdX.exec(cmd);
			// System.out.println(ret);
			// baseName=nextChar(baseName);
		}
	}

	public static String getCaptch(String jpg) {

		String newFile = new filex().getFilePathNFileMainName(jpg);
		String newfile2 = new filex().replaceExtname(jpg, "txt");

		String exepath = pathx.webAppPath() + "\\Tesseract\\tesseract.exe";
		String cmd = "\"" + exepath + "\"  \"" + jpg + "\" \"" + newFile
				+ "\"  -psm 7";
		if (dbg)
			System.out.println(cmd);
		List<String> li = new ArrayList();
		li.add(exepath);
		li.add(jpg);
		li.add(newFile);
		li.add("-psm");
		li.add("7");

		String ret = CmdX.exec_EX(cmd, li);
		String t = filex.read_NSF(newfile2).trim();
		return t;

	}

	public static String nextChar(String baseName) {
		byte[] ca = baseName.getBytes();
		int now = ca[0];
		now++;
		char c = (char) now;

		return String.valueOf(c);
	}

}
