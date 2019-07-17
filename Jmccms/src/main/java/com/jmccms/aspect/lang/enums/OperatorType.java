package com.jmccms.aspect.lang.enums;

/**
 * @Description: 业务操作类型
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.aspect.enums
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-04 21:30
 * @Email chen87647213@163.com
 */
public enum OperatorType {

    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 查看
     */
    SELECT(1, "查看"),

    /**
     * 新增
     */
    INSERT(2, "新增"),

    /**
     * 修改
     */
    UPDATE(3, "修改"),

    /**
     * 删除
     */
    DELETE(4, "删除"),

    /**
     * 授权
     */
    GRANT(5, "授权"),

    /**
     * 导出
     */
    EXPORT(6, "导出"),

    /**
     * 导入
     */
    IMPORT(7, "导入"),

    /**
     * 生成代码
     */
    REVIEW(9, "生成代码"),

    /**
     * 清空数据
     */
    CLEAN(8, "清空数据"),

    /**
     * 生成代码
     */
    JUMP(10, "跳转页面");

    private int value;
    private String reasonPhrase;


    OperatorType(int value, String reasonPhrase) {
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

