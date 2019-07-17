package com.jmccms.entity;

/**
 * 处理生成结果
 * @author Administrator
 *
 */
public class Result {
	private boolean success;
	private Object message;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Result(boolean success,Object message) {
		super();
		this.success = success;//成功
		this.message = message;
	}
	
	public Result(Object message) {
		super();
		this.message = message;
	}
	
}
