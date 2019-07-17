package com.jmccms.aspect.lang.annotation;

import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @Description: 自定义操作日志记录注解
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.aspect.lang
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-04 21:28
 * @Email chen87647213@163.com
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    OperatorType businessType() default OperatorType.OTHER;

    /**
     * 操作人类别
     */
    BusinessType operatorType() default BusinessType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

}
