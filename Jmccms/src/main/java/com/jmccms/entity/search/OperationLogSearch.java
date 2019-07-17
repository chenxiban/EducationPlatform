package com.jmccms.entity.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 操作日志查询帮助类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.entitySearch
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-11 21:47
 * @Email chen87647213@163.com
 */
@Data // 自动生成set方法,自动生成get方法
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
public class OperationLogSearch implements Serializable {

    /**
     * 操作内容
     */
    private String logOperatingContent;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthStart;// 创建开始时间检索
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthEnd;// 创建结束时间检索

    /**
     * 操作类型
     */
    private String logOperationType;

    /**
     * 操作状态
     */
    private String logOperatingResults;

    private int page = 0;
    private int rows = 10;

}
