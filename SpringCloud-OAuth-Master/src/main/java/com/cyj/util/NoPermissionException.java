package com.cyj.util;

/**
 * 自定义异常
 * @Description:   没有访问权限异常
 * @author         Mashuai 
 * @Date           2018-5-10 下午7:30:45  
 * @Email          1119616605@qq.com
 */
@SuppressWarnings("serial")
public class NoPermissionException extends Exception{
	
	public NoPermissionException(){
		super("没有访问权限异常");
	}
	
	public NoPermissionException(String info){
		super(info);
	}

}
