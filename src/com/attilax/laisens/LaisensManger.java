package com.attilax.laisens;

import java.util.Date;

//import com.attilax.util.CstGettor;
import com.attilax.util.DateUtil;

@Deprecated
public class LaisensManger {
	
	public static void main(String[] args) throws OverTimeEx {
		checkOvertime("2015-03-12",CstGettor.getDateFrmNet());
		System.out.println("---");
		//org.apache.commons.fileupload.servlet.ServletFileUpload
	}
	@Deprecated
	public static boolean isOvertime(String date_s) {
	    Date d= DateUtil.str2date(date_s, false);
	    if(d.getTime()<new Date().getTime())return true;
		return false;
	}
	/**
	 * 
	 * @param date_s   yyyy_mm_dd
	 * @return
	 * @throws OverTimeEx 
	 */
	public static void checkOvertime(String date_s,Date dt) throws OverTimeEx {
	    @SuppressWarnings("deprecation")
		Date d= DateUtil.str2date(date_s, false);
	    if(dt.getTime()>d.getTime()) 
	    	throw new OverTimeEx(" OverTimeEx");
	}
	
	private boolean isOvertime_3month(String last_check_time) {
		 Date d= DateUtil.str2date(last_check_time, false);
		 if( DateUtil.getDayInterval(d,new Date())>90)
		   return true;
		return false;
	}
	
	public boolean checkOvertime_1y(String last_check_time) throws OverTimeEx {
		 Date last_check_time_date= DateUtil.str2date(last_check_time, false);
		 Date nowDate = new Date();
		 if(nowDate.getTime()>last_check_time_date.getTime())
		if( DateUtil.getDayInterval(last_check_time_date,nowDate)>350)
			 throw new OverTimeEx(" OverTimeEx");
		return false;
	}
	
	
	public boolean checkOvertimeV2(String last_check_time) throws OverTimeEx {
		 Date last_check_time_date= DateUtil.str2date(last_check_time, false);
		 Date nowDate = new Date();
		 if(nowDate.getTime()>last_check_time_date.getTime())		     
			 throw new OverTimeEx(" OverTimeEx");
		return false;
	}

}
