package com.jmccms.service;

import com.jmccms.entity.Role;
import com.jmccms.entity.search.RoleSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description: 角色业务层
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:11
 * @Email chen87647213@163.com
 */
public interface RoleService {

    /**
     * 分页多条件检索查询用户详情
     * @param roleSearch
     * @param pageable
     * @return
     */
    Page<Role> sreachByRole(RoleSearch roleSearch, Pageable pageable);

    /**
     * 添加角色
     * @param Role
     * @return
     */
    boolean addRole(Role Role);

    /**
     * 查询角色名称
     */
    Role findByRoleName(String roleName);

    /**
     * 修改角色
     * @param roleId
     * @return
     */
    //boolean updRoleById(Role Role);

    Role getRoleById(Long roleId);

    boolean updateRole(Role roleId);


    /**
     *  根据角色id删除角色支持批量删除
     * @param roleList
     * @return
     */
    boolean deleteRole(String roleList);

    /**
     * 获取所有用户信息
     * @return
     */
    List<Role> getRoleLists();

    /**
     * 根据用户id查询该用户拥有的角色
     * @param id
     * @return
     */
    List<Role> getUserRoleByUserId(Integer id);

    /**
     * 根据用户id和角色id为用户添加角色
     * @param roleId
     * @param userId
     * @return
     */
    boolean addUserAndRole(String roleId, Integer userId);

    /**
     * 为用户移除角色
     * @param roleId
     * @param usersId
     * @return
     */
    boolean deleteByRoleId(String roleId,Integer usersId);

}
