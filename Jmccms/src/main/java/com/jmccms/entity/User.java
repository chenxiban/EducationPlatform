package com.jmccms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 用户类
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 15:32
 * @Email chen87647213@163.com
 */
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
@Entity
@Table(name = "jmccms_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '用户id'")
    private Long userId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '用户名称'  ")
    private String userName;
    @Column(columnDefinition = "varchar(100) NOT NULL COMMENT '用户密码'  ")
    private String userPassWord;
    @Column(columnDefinition = "datetime COMMENT '用户创建时间' ")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Date userCreateTime;
    @Column(columnDefinition = "datetime COMMENT '用户最后一次登录时间' ")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Date userLastLoginTime;
    @Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Timestamp userLastUpdateTime;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '创建人'  ")
    private String userFounder;
    @Column(columnDefinition = "varchar(64) COMMENT '修改人'  ")
    private String userUpdateMan;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER) // 指定多对多关系
    @Cascade(value = { CascadeType.ALL }) // 设置级联关系
    @JoinTable(name = "jmccms_user_role", // 指定第三张中间表名称
            joinColumns = { @JoinColumn(name = "user_id") }, // 本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
            inverseJoinColumns = { @JoinColumn(name = "role_id") }) // 多对多关系另一张表与第三张中间表表的外键的对应关系
    @NotFound(action = NotFoundAction.IGNORE) // NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
    private Set<Role> rolesSet = new HashSet<>();// 用户所拥有的角色集合

}
