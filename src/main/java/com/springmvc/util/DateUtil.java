package com.springmvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DateUtil {

	private static ConcurrentLinkedQueue<SimpleDateFormat> queue = new ConcurrentLinkedQueue<>();
	
	private static final String DEFAULT_PARTTERN = "yyyy-MM-dd HH:mm:ss";
	
	static{
		for(int i=0; i<10; i++){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_PARTTERN);
			queue.add(simpleDateFormat);
		}
	}
	
	public static SimpleDateFormat getFormatObj(){
		SimpleDateFormat sdf  = null;
		for(;;){
			sdf = queue.poll();
			if(sdf != null){
				break;
			} 
		}
		return sdf;
	}
	
	public static void revertFormatObj(SimpleDateFormat sdf){
		queue.add(sdf);
	}
	
	public static  String formatDate(Date date){
		SimpleDateFormat formatObj = getFormatObj();
		String target = formatObj.format(date);
		revertFormatObj(formatObj);
		return target;
	}
	
	public Date parseDate(String dateStr){
		SimpleDateFormat formatObj = getFormatObj();
		
		Date target = null;
		try {
			target = formatObj.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		revertFormatObj(formatObj);
		return target;
	}
	
	public static  String formatDate(Date date,String parttern){
		SimpleDateFormat formatObj = getFormatObj();
		
		formatObj.applyPattern(parttern);
		String target = formatObj.format(date);
		formatObj.applyPattern(DEFAULT_PARTTERN);
		
		revertFormatObj(formatObj);
		return target;
	}
}
