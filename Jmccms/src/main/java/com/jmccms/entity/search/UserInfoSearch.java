package com.jmccms.entity.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户详情模糊查询条件
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.entitySearch
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-03 18:05
 * @Email chen87647213@163.com
 */

@Data // 自动生成set方法,自动生成get方法
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
public class UserInfoSearch implements Serializable {

    // 用户名检索
    private String userInfoName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthdayStart;// 生日开始时间检索
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthdayEnd;// 生日结束时间检索
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthStart;// 创建开始时间检索
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthEnd;// 创建结束时间检索
    //用户性别检索
    private String userInfoSex;
    //用户手机号检索
    private String userInfoPhone;
    //用户邮箱检索
    private String userInfoEmail;
    //用户地址检索
    private String userInfoAddress;
    //用户创建人检索
    private String userInfoFounder;
    //用户是否锁定
    private String userInfoIsLookout;
    private int page = 0;
    private int rows = 10;

}

