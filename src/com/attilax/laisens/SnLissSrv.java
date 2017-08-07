/**
 * 
 */
package com.attilax.laisens;

import java.util.Date;

import com.attilax.device.HardWareUtils;
import com.attilax.io.filex;
import com.attilax.secury.AesV2q421;
import com.attilax.util.DateUtil;

/**
 * @author attilax
 *2016年12月5日 下午11:30:20
 */
public class SnLissSrv {
	
	public static void main(String[] args) {
		//new SnLissSrv().check();com  oWE9kBxPXL8OZDDsjc0P4Tlb3KDAX4IOhbatJpETUE6Oy3YPT4Oq9bNrb9hfx1Xd
	//	String cert=new SnLissSrv().geneCert();
		 new SnLissSrv().check();
		 new SnLissSrv().geneCert("c:\\sn2.txt");
		 System.out.println("--f");
	}
	
	
	/**
	attilax    2016年12月5日  下午11:33:54
	 * @return
	 */
	private String geneCert() {
		 String thisPc= new HardWareUtils().getSn();
		 String enc=AesV2q421.encrypt(thisPc);
		 System.out.println(enc);
		return enc;
	}
	
	private String geneCert(String f) {
		 String thisPc= new HardWareUtils().getSn();
		 String enc=AesV2q421.encrypt(thisPc);
		 System.out.println(enc);
		 filex.save(enc, f);
		return enc;
	}


	public   void check( ) throws SnEx {
	    @SuppressWarnings("deprecation")
		 String thisPc= new HardWareUtils().getSn();
	    
	    String certSn=filex.read_NSF("c:\\sn.txt");
	    certSn=certSn.trim();
	    String dec=AesV2q421.decrypt(certSn);
	    if(!thisPc.equals(dec) ) 
	    	throw new SnEx("certSn:"+dec+",nowSn:"+thisPc);
	}

}
