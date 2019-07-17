package com.jmccms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 操作日志记录表
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-05 23:10
 * @Email chen87647213@163.com
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
@Entity
@Table(name = "jmccms_operation_log")
public class OperationLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '操作日志表主键id'")
    private Long logOperatorId;

    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '操作者(存入操作用户的userId)'")
    private Long logOperator;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '操作者Ip'  ")
    private String logOperatorIp;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '操作者用于访问的系统'  ")
    private String logOperatingSystem;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '操作者用于访问的浏览器名称'  ")
    private String logOperationBrowser;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '操作者用于访问的浏览器版本'  ")
    private String logOperatingBrowserVersion;

    @Column(columnDefinition = "varchar(200) NOT NULL COMMENT '操作的类名称'  ")
    private String logOperatingClassName;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '操作的方法名称(存入控制器方法名称RequestMapping内的value保持一致)'  ")
    private String logOperatingMethodName;

    @Column(columnDefinition = "varchar(200) NOT NULL COMMENT '操作的控制器路径'  ")
    private String logOperatingPath;

    @Column(columnDefinition = "varchar(30) NOT NULL COMMENT '操作的请求类型'  ")
    private String logOperatingRequestType;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '操作内容(存入控制器方法名称RequestMapping内的name保持一致)'  ")
    private String logOperatingContent;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '操作类型(0:其他,1:查看,2:新增,3:修改,4:删除,5:授权,6:导出,7:导入,8:清空数据,9:审批)'  ")
    private String logOperationType;

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '操作结果(操作的执行状态:200,404这一类的)'  ")
    private String logOperatingResults;

    @Column(columnDefinition = "datetime NOT NULL COMMENT '操作创建时间' ")
    private Date logOperatingCreateTime;

}

