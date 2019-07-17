package com.jmccms.aspect.lang.annotation;

import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @Description: 操作日志记录处理
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.aspect.lang.annotation
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-04 21:34
 * @Email chen87647213@163.com
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    OperatorType operatetype() default OperatorType.OTHER;

    /**
     * 操作人类别
     */
    BusinessType vistortype() default BusinessType.MANAGE;

}
