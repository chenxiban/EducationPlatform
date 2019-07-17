package com.jmccms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 字典类型表
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-29 22:51
 * @Email chen87647213@163.com
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
@Entity
@Table(name = "jmccms_dic_type")
public class DicType implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(32) NOT NULL comment '备注:字典类型表主键id'  ")
    private String dtId;

    @Column(unique = true, columnDefinition = "varchar(20) NOT NULL comment '备注:字典类型'  ")
    private String dtName;

    @Column(columnDefinition = "varchar(200) NOT NULL comment '备注:字典类型备注信息'  ")
    private String dtRemark;

    @Column(columnDefinition = "char(1) NOT NULL DEFAULT 1 comment '备注:字典类型是否有用1：有用0：无用'  ")
    private String dtStatus;

    @Column(columnDefinition = "datetime COMMENT '字典类型创建时间' ")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Date createTime;

    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '创建人'  ")
    private String createUser;

    @Column(columnDefinition = "datetime COMMENT '最后一次修改时间'")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Date updateTime;

    @Column(columnDefinition = "varchar(64) COMMENT '更新人'  ")
    private String updateUser;

}
