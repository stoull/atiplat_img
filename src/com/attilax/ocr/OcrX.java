package com.attilax.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// r12 import aaBaolilo.ImageDemo;



import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.json.AtiJson;
import com.attilax.lang.CmdX;

public class OcrX {

	public static void main(String[] args) {
 //  "C:\Program Files (x86)\Tesseract-OCR\tesseract.exe" e:\a.jpg e:\a_ok.jpg -l chi_sim
//		String baseName="a";
//		String dir="c:\\bb";
//		 File dir2 = new File(dir); 
//		    File[] files = dir2.listFiles(); 
//		 for (File file : files) {
//			
//		   
//		 	ImageDemo demo = new ImageDemo();	
//			String bname=filex.getFileName_noExtName(file.getAbsolutePath());
//			String exname=filex.getExtName(file.getAbsolutePath());
//			String bin_jpg=dir+"\\"+bname+"_bin."+exname;
//			try {
//				demo.binaryImage(file.getAbsolutePath(),bin_jpg);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(getCaptch(bin_jpg));
//			
////			String cmd="\"C:\\Program Files (x86)\\Tesseract-OCR\\tesseract.exe\" e:\\"+baseName+".jpg e:\\"+baseName+"_ok.jpg -psm 8";
////			String ret=CmdX.exec(cmd);
////			System.out.println(ret);
////			 baseName=nextChar(baseName);
//		}


	}
	public static String getCaptch(String jpg) {
		String baseName=filex.getFileName_noExtName(jpg);
		File f=new File(jpg);
		String parentPath=f.getParentFile().getAbsolutePath();
		String newFile=parentPath+"\\"+baseName;
		String newfile2=newFile+".txt";
		
		
String exepath=pathx.classPathParent()+"\\Tesseract\\tesseract.exe";
		String cmd="\""+exepath+"\"  \""+jpg+"\" \""+newFile+"\"  -psm 7";
		System.out.println(cmd);
	List<String> li=new ArrayList();
	li.add(exepath);li.add(jpg);li.add(newFile);li.add("-psm");li.add("7");
		
		String ret=CmdX.exec_EX(cmd,li);
		String t=filex.read_NSF(newfile2).trim();
		return t;
		
	}
	

	public static String nextChar(String baseName) {
		byte[] ca=baseName.getBytes();
		int now=ca[0];
		now++;
		char c=(char) now;
		
		return String.valueOf(c);
	}
	/**
	attilax    2016骞�11鏈�1鏃�  涓嬪崍9:17:28
	 * @param string
	 * @param jpg
	 * @return
	 */
	public String getCaptch(String tesseract_orcMaindir, String jpg) {
		String baseName=filex.getFileName_noExtName(jpg);
		File f=new File(jpg);
		String parentPath=f.getParentFile().getAbsolutePath();
		String newFile=parentPath+"\\"+baseName;
		String newfile2=newFile+".txt";
		
		
String exepath=tesseract_orcMaindir+"\\tesseract.exe";
		String cmd="\""+exepath+"\"  \""+jpg+"\" \""+newFile+"\"  -psm 7";
		System.out.println(cmd);
	List<String> li=new ArrayList();
	li.add(exepath);li.add(jpg);li.add(newFile);li.add("-psm");li.add("7");
		
		String ret=CmdX.exec_EX(cmd,li);
		String t=filex.read_NSF(newfile2).trim();
		return t;
	}

	public String getCaptch_cn(String tesseract_orcMaindir, String jpg) {
		String baseName = filex.getFileName_noExtName(jpg);
		File f = new File(jpg);
		String parentPath = f.getParentFile().getAbsolutePath();
		String newFile = parentPath + "\\" + baseName;
		String newfile2 = newFile + ".txt";

		String exepath = tesseract_orcMaindir + "\\tesseract.exe";
		String cmd = "\"" + exepath + "\"  \"" + jpg + "\" \"" + newFile + "\"  -psm 7";
		// System.out.println(cmd);
		List<String> li = new ArrayList();
		li.add(exepath);
		li.add(jpg);
		li.add(newFile);
		li.add("-psm");
		li.add("7");
		li.add("-l");
		li.add("chi_sim");

		String ret = CmdX.exec_EX(cmd, li);
		String t = filex.read_NSF(newfile2).trim();
		return t;
	}

	public String getTxt(String orcMaindir, String jpg) {
		String baseName = filex.getFileName_noExtName(jpg);
		File f = new File(jpg);
		String parentPath = f.getParentFile().getAbsolutePath();
		String newFile = parentPath + "\\" + baseName;
		String newfile2 = newFile + ".txt";

		String exepath = orcMaindir + "\\tesseract.exe";
		String cmd = "\"" + exepath + "\"  \"" + jpg + "\" \"" + newFile
				+ "\" "; // -psm 7
	//	System.out.println(cmd);
		List<String> li = new ArrayList();
		li.add(exepath);
		li.add(jpg);
		li.add(newFile);
		li.add("-l");
		li.add("chi_sim");
		 System.out.println( AtiJson.toJson(li));
	//	li.add("-psm");
	//	li.add("7");
			//not use cmd .only li..
		String ret = CmdX.exec_EX(cmd, li);
		String t = filex.read_NSF(newfile2).trim();
		return t;
	}
	
	public String getTxt_en(String orcMaindir, String jpg) {
		String baseName = filex.getFileName_noExtName(jpg);
		File f = new File(jpg);
		String parentPath = f.getParentFile().getAbsolutePath();
		String newFile = parentPath + "\\" + baseName;
		String newfile2 = newFile + ".txt";

		String exepath = orcMaindir + "\\tesseract.exe";
//		String cmd = "\"" + exepath + "\"  \"" + jpg + "\" \"" + newFile
//				+ "\" "; // -psm 7
		//System.out.println(cmd);
		List<String> li = new ArrayList();
		li.add(exepath);
		li.add(jpg);
		li.add(newFile);
		 
		 System.out.println( AtiJson.toJson(li));
	//	li.add("-psm");
	//	li.add("7");
			//not use cmd .only li..
		String ret = CmdX.exec_EX("", li);
		String t = filex.read_NSF(newfile2).trim();
		return t;
	}
	
	public static String exec(String cmd) {
 		try {  
 		   // 鎵ц CMD 鍛戒护  
 		   Process process = Runtime.getRuntime().exec(cmd);  
 		//  process.geto
 		   // 浠庤緭鍏ユ祦涓鍙栨枃鏈�  
 		   BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));  
 		     String r="";
 		   // 鏋勯�犱竴涓啓鍑烘祦骞舵寚瀹氳緭鍑烘枃浠朵繚瀛樿矾寰�  
 		//   FileWriter fw = new FileWriter(new File("C:/Users/Administrator/Desktop/CmdInfo.txt"));  
 		     
 		   String line = null;  
 		     
 		   // 寰幆璇诲彇  
 		   while ((line = reader.readLine()) != null) {  
 		    // 寰幆鍐欏叆  
 		  r=r+(line + "\n");  
 		   }  
 		     
 		 
 		   // 鍏抽棴杈撳嚭娴�  
 		   process.getOutputStream().close();  
 		     
 		   System.out.println("cmd ext finish!");  
 		   return r;
 		  } catch (Exception e) {  
 			 return core.toJsonStrO88(e);
 		  }
		
 	}  


}
