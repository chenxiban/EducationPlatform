package com.jmccms.util;

import com.comment.constantenums.ResultCode;

import java.io.Serializable;

/**
 * @Description: 返回的结果封装类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.util
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-18 21:19
 * @Email chen87647213@163.com
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    /**
     * 成功 不返回数据直接返回成功信息
     *
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功 并且加上返回数据
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 成功 自定义成功返回状态 加上数据
     *
     * @param resultCode
     * @param data
     * @return
     */
    public static Result success(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     * 单返回失败的状态码
     *
     * @param resultCode
     * @return
     */
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回失败的状态码 及 数据
     *
     * @param resultCode
     * @param data
     * @return
     */
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

}
