package com.jmccms.util;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Description: Base64Util加密帮助类
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.util
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-03 22:24
 * @Email chen87647213@163.com
 */
public class Base64Util {

	/**
	 * BASE64解密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String key) throws Exception {
		if(StringUtils.isEmpty(key)) {
			return null;
		}
        byte[] bytes = (new BASE64Decoder()).decodeBuffer(key);
        return new String(bytes);
    }
 
    /**
     * BASE64加密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key.getBytes());
    }
    public static String encryptBASE64UTF8(String key) throws Exception {
    	
    	return (new BASE64Encoder()).encode(key.getBytes()).replace("\n", "").replace("\r", "");
    	
    }
    
   /* public static void main(String[] args) {
    	try {
	    	String data = Base64Util.decryptBASE64("e2NvcnBJZDond3g5MjM0MmExNWUxZGJhYjc0JyxhZ2VudElkOicxMDAwMDE0J30=");
			String data1 = Base64Util.decryptBASE64("aHR0cDovL2N4amdnbGZ0LmNwaWMuY29tLmNuL2N4amdnbC9waG9uZW1hcA==");
			String data4 = Base64Util.decryptBASE64("aHR0cDovL2N4amdnbGZ0LmNwaWMuY29tLmNuL2N4amdnbC9jb21tb24vdG9KZ2dsP2N4amdnbD1taWNDbGFzcy9tb2JpbGU=");
			String data2 = Base64Util.encryptBASE64("http://cxjgglft.cpic.com.cn/cxjggl/phonemap");
			String data3 = Base64Util.encryptBASE64("http://cxjgglft.cpic.com.cn/cxjggl/common/toJggl?cxjggl=mobile");
//			String userId = MD5Util.getMD5("722334");
    	} catch (Exception e) {
    		
    	}
	}*/
}
