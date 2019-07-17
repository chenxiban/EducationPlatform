package com.jmccms.entity.search;

import com.jmccms.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 角色模糊查询条件
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.entitySearch
 * @Author: GuoShiCai
 * @CreateTime: 2019-05-15 19:45
 * @Email 16601722947@163.com
 */

@Data // 自动生成set方法,自动生成get方法
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
public class RoleSearch implements Serializable {

    private String roleName;
    private String roleTrueName;
    private String roleCreateMan;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthdayStart;// 生日开始时间检索
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthdayEnd;// 生日结束时间检索
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthStart;// 创建开始时间检索
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date birthEnd;// 创建结束时间检索

    private int page = 0;
    private int rows = 10;

}
