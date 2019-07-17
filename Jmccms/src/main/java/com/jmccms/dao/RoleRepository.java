package com.jmccms.dao;

import com.jmccms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: 角色接口
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.dao
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:03
 * @Email chen87647213@163.com
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    /**
     * 根据角色id查询角色信息
     *
     * @param RoleId
     * @return
     */
    Role findByRoleId(Long RoleId);

    /**
     * 根据角色名称查询角色信息
     *
     * @param RoleName
     * @return
     */
    Role findByRoleName(String RoleName);

    /**
     * 批量删除角色信息
     *
     * @param roleList
     * @return
     */
    @Query(value = "DELETE FROM jmccms_role WHERE role_id IN (:roleList)", nativeQuery = true)
    @Modifying
    @Transactional
    Integer deleteRole(@Param(value = "roleList") List<String> roleList);

    /**
     * 根据用户id查询该用户拥有的角色
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM jmccms_role r LEFT JOIN jmccms_user_role u ON u.role_id=r.role_id WHERE 1=1 AND u.user_id=:userId", nativeQuery = true)
    List<Role> getUserRoleByUserId(@Param("userId") Integer userId);

    /**
     * 为用户添加角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    @Query(value = "INSERT INTO jmccms_user_role (role_id,user_id) VALUES(:roleId,:userId)", nativeQuery = true)
    @Modifying
    @Transactional
    Integer addByRole(@Param(value = "roleId") Integer roleId, @Param(value = "userId") Integer userId);

    /**
     * 删除角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    @Query(value = "DELETE FROM jmccms_user_role WHERE 1=1 AND role_id IN (:roleId) AND user_id=:userId", nativeQuery = true)
    @Modifying
    @Transactional
    Integer deleteByRoleId(@Param(value = "roleId") List<String> roleId, @Param(value = "userId") Integer userId);

}
