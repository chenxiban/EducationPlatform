package com.jmccms.util;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @Description: 权限实体类
 * @BelongsProject: jmccms
 * @BelongsPackage: com.jmccms.jmccms.util
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 14:35
 * @Email chen87647213@163.com
 */
@Data
public class SysPermission implements Serializable {

    private Integer permission_id;//权限ID
    private String permission_value;//权限
    private String permission_module;//权限所属模块
    private String permission_name;//权限备注说明介绍
    private Timestamp permission_last_update_time;//权限修改日期时间

    public SysPermission(String permission_value, String permission_module,
                         String permission_name) {
        super();
        this.permission_value = permission_value;
        this.permission_module = permission_module;
        this.permission_name = permission_name;
    }

}
