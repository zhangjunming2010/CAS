package com.tinymore.cas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BaseUtil {

	/**
	 * 获取UUID
	 * @return
	 */
	public static String UUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
	
	/**
	 * 获取系统时间
	 * @return
	 */
	public static String SysteTime() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
