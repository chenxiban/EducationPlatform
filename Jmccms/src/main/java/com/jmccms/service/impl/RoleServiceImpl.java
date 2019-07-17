package com.jmccms.service.impl;

import com.jmccms.dao.RoleRepository;
import com.jmccms.entity.Role;
import com.jmccms.entity.search.RoleSearch;
import com.jmccms.service.RoleService;
import com.jmccms.util.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 角色实现
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.serviceimpl
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:23
 * @Email chen87647213@163.com
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    /**
     * 分页多条件检索查询角色
     *
     * @param roleSearch
     * @param pageable
     * @return
     */
    public Page<Role> sreachByRole(RoleSearch roleSearch, Pageable pageable) {
        log.info("进入用户详情分页多条件检索查询方法，所得到的参数userInfoSearch为：{},分页参数pageable：{}", roleSearch.toString(), pageable.toString());
        Page<Role> roles = null;
        try {
            roles = roleRepository.findAll(this.getWhereClause(roleSearch), pageable);
            log.info("用户详情分页多条件检索查询方法为空检索成功");
        } catch (Exception e) {
            log.error("用户详情分页多条件检索查询方法,userInfos集合为空检索失败", e);
        }
        return roles;
    }

    /**
     * 查询条件动态组装 动态生成where语句 匿名内部类形式
     *
     * @param r
     * @return
     * @author ChenYongJia
     */
    @SuppressWarnings({"serial"})
    private Specification<Role> getWhereClause(final RoleSearch r) {
        return new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();// 动态SQL表达式
                List<Expression<Boolean>> exList = predicate.getExpressions();// 动态SQL表达式集合

                // 用户名称检索
                if (!IsEmptyUtils.isEmpty(r.getRoleName())) {
                    exList.add(cb.like(root.<String>get("roleName"), "%" + r.getRoleName() + "%"));
                }
                // 创建时间检索
                if (!IsEmptyUtils.isEmpty(r.getBirthStart())) {
                    exList.add(cb.greaterThanOrEqualTo(root.<Date>get("roleCreatTime"), r.getBirthStart()));
                }
                // 创建时间检索
                if (!IsEmptyUtils.isEmpty(r.getBirthEnd())) {
                    exList.add(cb.lessThanOrEqualTo(root.<Date>get("roleCreatTime"), r.getBirthEnd()));
                }
                return predicate;
            }

        };
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public boolean addRole(Role role) {
        log.info("进入添加角色方法,获取到的参数为role:{}", role.toString());
        try {
            roleRepository.save(role);
            return true;
        } catch (Exception e) {
            log.error("添加角色方法调用失败,e:{}", e);
            return false;
        }
    }

    /**
     * 查询角色名称
     *
     * @param roleName
     * @return
     */
    @Override
    public Role findByRoleName(String roleName) {
        log.info("进入查询角色名称方法,获取到的参数为roleName:{}", roleName);
        return roleRepository.findByRoleName(roleName);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @Override
    public Role getRoleById(Long roleId) {
        log.info("进入修改角色方法,获取到的参数为roleId:{}", roleId);
        return roleRepository.findByRoleId(roleId);
    }

    @Override
    public boolean updateRole(Role roleId) {
        try {
            roleRepository.save(roleId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    /**
     * 根据角色id删除角色支持批量删除
     *
     * @param roleList
     * @return
     */
    @Override
    public boolean deleteRole(String roleList) {
        log.info("进入根据角色id删除角色支持批量删除方法,获取的参数为roleList:{}", roleList);
        try {
            List<String> list = new ArrayList<>();
            String[] ids = roleList.split(",");
            for (String idArr : ids) {
                list.add(idArr);
            }
            System.out.println(list);
            roleRepository.deleteRole(list);
            return true;
        } catch (Exception e) {
            log.error("调用根据角色id删除角色支持批量删除方法失败", e);
            return false;
        }
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    public List<Role> getRoleLists() {
        log.info("进入获取所有用户信息方法");
        return roleRepository.findAll();
    }

    /**
     * 根据用户id查询该用户拥有的角色
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> getUserRoleByUserId(Integer id) {
        log.info("进入根据用户id查询该用户拥有的角色方法,获取的参数为id:{}", id);
        try {
            return roleRepository.getUserRoleByUserId(id);
        } catch (Exception e) {
            log.error("调用根据用户id查询该用户拥有的角色方法失败", e);
            return null;
        }
    }

    /**
     * 根据用户id和角色id为用户添加角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    @Override
    public boolean addUserAndRole(String roleId, Integer userId) {
        log.info("进入根据用户id和角色id为用户添加角色方法,获取的参数为roleId:{},userId:{}", roleId, userId);
        try {
            List<String> list = new ArrayList<String>();
            String[] ids = roleId.split(",");
            for (String role : ids) {
                list.add(role);
            }
            System.out.println(list);
            for (String roleIds : list) {
                roleRepository.addByRole(Integer.parseInt(roleIds), userId);
            }
            return true;
        } catch (NumberFormatException e) {
            log.error("调用根据用户id和角色id为用户添加角色方法失败", e);
            return false;
        }
    }

    /**
     * 为用户移除角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    public boolean deleteByRoleId(String roleId, Integer userId) {
        log.info("进入为用户移除角色方法,获取的参数为roleId:{},userId:{}", roleId, userId);
        try {
            List<String> list = new ArrayList<>();
            String[] ids = roleId.split(",");
            for (String role : ids) {
                list.add(role);
            }
            if (roleRepository.deleteByRoleId(list, userId) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            log.error("调用为用户移除角色方法失败", e);
            return false;
        }
    }

}
