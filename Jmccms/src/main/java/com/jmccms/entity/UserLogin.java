package com.jmccms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户登录日志表
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-30 18:18
 * @Email chen87647213@163.com
 */
@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
@Entity
@Table(name = "jmccms_user_login")
public class UserLogin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned COMMENT '登录日志id'")
    private Long userLoginId;

    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '登录名称'  ")
    private String userLoginName;

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '登录地址'  ")
    private String userLoginIp;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '登陆地点'  ")
    private String userLoginAddress;

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '浏览器'  ")
    private Long userLoginBrowser;

    @Column(columnDefinition = "varchar(20) NOT NULL  COMMENT '操作系统'  ")
    private String userLoginSystem;

    @Column(columnDefinition = "char(1) DEFAULT 0 COMMENT '登录状态(0:失败 1:成功)'  ")
    private String userLoginStatus;

    @Column(columnDefinition = "timestamp COMMENT '登陆时间'", nullable = false, updatable = false, insertable = false)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")    //日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    //接受::字符串日期需要格式化为日期类型
    private Date userInfoLastUpdateTime;

}
