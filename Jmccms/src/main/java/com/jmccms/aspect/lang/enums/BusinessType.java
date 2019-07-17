package com.jmccms.aspect.lang.enums;

/**
 * @Description: 操作人类别
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.aspect.enums
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-04 21:29
 * @Email chen87647213@163.com
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 后台用户
     */
    MANAGE(1, "后台用户"),

    /**
     * 手机端用户
     */
    MOBILE(2, "手机端用户");
    private int value;
    private String reasonPhrase;

    BusinessType(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }


}
