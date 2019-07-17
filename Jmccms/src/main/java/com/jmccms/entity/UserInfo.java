package com.jmccms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description: 用户详情表
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 17:25
 * @Email chen87647213@163.com
 */
@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
@Entity
@Table(name = "jmccms_user_info")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '用户详情id'")
    private Long userInfoId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '用户名称'  ")
    private String userInfoName;
    @Column(columnDefinition = "varchar(200) COMMENT '用户详细地址'  ")
    private String userInfoAddress;
    @Column(columnDefinition = "varchar(11) NOT NULL COMMENT '用户电话'  ")
    private String userInfoPhone;
    @Column(columnDefinition = "tinyint unsigned DEFAULT 1 COMMENT '用户年龄'  ")
    private Long userInfoAge;
    @Column(columnDefinition = "char(1) DEFAULT 0 COMMENT '用户性别(0:男 1:女)'  ")
    private String userInfoSex;
    @Column(columnDefinition = "date COMMENT '用户生日'  ")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date userInfoBirthday;
    @Column(columnDefinition = "varchar(100) NOT NULL COMMENT '用户邮箱地址'  ")
    private String userInfoEmail;
    @Column(columnDefinition = "char(1) DEFAULT '否'  COMMENT '是否锁定'")
    private String userInfoIsLookout;
    @Column(columnDefinition = "datetime COMMENT '用户锁定时间' ")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Date userInfoLockTime;
    @Column(columnDefinition = "tinyint unsigned DEFAULT 0 COMMENT '密码错误次数'")
    private Integer userInfoPsdWrongTime;
    @Column(columnDefinition = "varchar(100) COMMENT '用户简介描述'  ")
    private String userInfoRemark;
    @Column(columnDefinition = "datetime COMMENT '创建时间' ")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Date userInfoCreateTime;
    @Column(columnDefinition = "datetime COMMENT '最后一次修改时间'")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Date userInfoLastUpdateTime;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '创建人'  ")
    private String userInfoFounder;
    @Column(columnDefinition = "varchar(64) COMMENT '修改人'  ")
    private String userInfoUpdateMan;

}
