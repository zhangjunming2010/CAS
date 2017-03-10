package com.tinymore.cas.utils;

import org.glassfish.jersey.internal.util.Base64;

public class Base64Util {

	/**
	 * 加密
	 * @param bytes
	 * @return
	 */
    public static String encode(String str) {  
    	return Base64.encodeAsString(str.getBytes());
    } 
    
    /**
     * 解密
     * @param bytes
     * @return
     */
    public static String decode(String str) {  
        return Base64.decodeAsString(str.getBytes());
    }  
	

}
