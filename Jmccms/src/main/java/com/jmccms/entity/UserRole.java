package com.jmccms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户角色中间表
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-04 21:19
 * @Email chen87647213@163.com
 */
@Data
public class UserRole implements Serializable {

    // 用户id辅助字段
    private Integer userId;
    // 角色id辅助字段
    private String roleId;
    // 辅助区分操作类型
    private Integer p;

}
