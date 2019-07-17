package com.jmccms.dao;

import com.jmccms.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: 权限接口
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.dao
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:02
 * @Email chen87647213@163.com
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

    /**
     * 根据text名称查询所属孩子节点
     * 使用自定义工具类转换时原生SQL结果集
     * @return
     */
    @Query(value="SELECT permission_id,permission_name,permission_url,permission_last_update_time,permission_value,permission_module,permission_create_time FROM jmccms_permission GROUP BY permission_module ORDER BY permission_id",nativeQuery=true)
    List<Permission> findsGroup();

    /**
     * 根据text名称查询所属孩子节点
     * 使用自定义工具类转换时原生SQL结果集
     * @param permissionModule
     * @return
     */
    @Query(value="SELECT permission_id,permission_name,permission_url,permission_last_update_time,permission_value,permission_module,permission_create_time FROM jmccms_permission WHERE permission_module=:permissionModule",nativeQuery=true)
    List<Permission> findsByPermissionModule(@Param(value="permissionModule") String permissionModule);

}
