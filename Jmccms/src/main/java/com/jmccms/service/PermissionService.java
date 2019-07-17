package com.jmccms.service;

import com.jmccms.entity.Permission;

import java.util.List;

/**
 * @Description: 权限业务层
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:11
 * @Email chen87647213@163.com
 */
public interface PermissionService {

    /**
     * 查询所有权限集合
     * @return 权限字符串集合
     * @author Chenyongjia
     */
    List<Permission> queryNode();

    /**
     * 查询所有权限集合
     * @return 权限字符串集合
     * @author Chenyongjia
     */
    List<String> queryAll();

    /**
     * 批量插入权限数据
     * @param pList
     * @return 成功插入的权限数据条数
     * @author Chenyongjia
     */
    Integer batchInsert(List<Permission> pList);

}
