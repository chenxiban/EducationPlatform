package com.jmccms.controller;

import com.jmccms.aspect.lang.annotation.LogRecord;
import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;
import com.jmccms.entity.Result;
import com.jmccms.entity.Role;
import com.jmccms.entity.UserRole;
import com.jmccms.entity.search.RoleSearch;
import com.jmccms.service.RoleService;
import com.jmccms.service.UserService;
import com.jmccms.util.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 角色控制器
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:28
 * @Email chen87647213@163.com
 */
@Slf4j
@RestController
@RequestMapping(value = "/role", name = "角色模块")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    /**
     * 查询所有角色模块 http://localhost:8066/jmccms/role/getAllRoles
     *
     * @param roleSearch
     * @return
     */
    @LogRecord(title = "查询所有角色模块", operatetype = OperatorType.SELECT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/getAllRoles", name = "查询所有角色模块", method = RequestMethod.GET)
    public Map<String, Object> getAllRoles(RoleSearch roleSearch) {
        log.info("进入查询所有用户模块方法控制器,获得到的roleSearch参数为:"+roleSearch.toString());
        Map<String, Object> map = null;
        try {
            Pageable pageable = PageRequest.of(roleSearch.getPage() -1, roleSearch.getRows(), Sort.Direction.ASC,
                    "roleId");
            Page<Role> page = roleService.sreachByRole(roleSearch, pageable);
            Long total = page.getTotalElements();
            List<Role> list = page.getContent();

            map = new HashMap<String, Object>();
            map.put("total", total);
            map.put("rows", list);
        } catch (Exception e) {
            log.error("调用查询所有角色模块方法查询失败", e);
        }
        return map;
    }

    /**
     * 添加角色
     * http://localhost:8066/jmccms/role/addRole
     * @param role
     * @return
     */
    @LogRecord(title = "添加角色", operatetype = OperatorType.INSERT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value="/addRole",name="添加角色",method = RequestMethod.GET)
    public Object addRole(Role role) {
        Role r=roleService.findByRoleName(role.getRoleName());
        role.setRoleCreateTime(new Date());
        role.setRoleName("你好");
        role.setRoleCreateMan("郭士才");
        role.setRoleWeight(2);
        if (IsEmptyUtils.isEmpty(r)) {
            if (roleService.addRole(role)) {
                return new Result(true,"添加成功");
            } else {
                return new Result(false,"添加失败");
            }
        } else {
            return new Result (false ,"该角色已近存在,请您重新添加");
        }
    }

    /**
     * 修改角色
     * http://localhost:8066/jmccms/role/updateRole&roleId=23
     * @param role
     * @return
     */
    @LogRecord(title = "修改角色", operatetype = OperatorType.UPDATE, vistortype = BusinessType.MANAGE)
    @RequestMapping(value="/updateRole",name="修改角色", method = RequestMethod.PUT)
    public Object updateRole(Role role) {
        Role role2=roleService.getRoleById(role.getRoleId());
        role2.setRoleName("大大");
        if (roleService.updateRole(role2)) {
            return new Result(true,"修改成功");
        } else {
            return new Result(false,"修改失败");
        }
    }


    /**
     * 为用户设置角色不带分页
     * http://localhost:8066/jmccms/role/getRoleList 不带分页
     *
     * @return
     */
    @LogRecord(title = "为用户设置角色不带分页", operatetype = OperatorType.GRANT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/getRoleList", name = "为用户设置角色不带分页", method = RequestMethod.GET)
    public Map<String, Object> getRoleList(@RequestParam(value = "userId", required = false) Integer userId) {
        log.info("进入不分页查询角色控制器");
        Map<String, Object> map = new HashMap<>();
        map.put("right", roleService.getUserRoleByUserId(userId));
        map.put("left", roleService.getRoleLists());
        return map;
    }

    /**
     * 为用户设置角色 http://localhost:8066/jmccms/role/setByRole
     *
     * @param userRole
     * @return
     */
    @LogRecord(title = "为用户设置角色", operatetype = OperatorType.GRANT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/setByRole", name = "为用户设置角色", method = RequestMethod.POST)
    public boolean setByRole(@RequestBody UserRole userRole) {
        log.info("进入为用户设置角色控制器");
        // 参数为1执行添加,为2执行移除
        if (userRole.getP()==1) {
            return roleService.addUserAndRole(userRole.getRoleId(),userRole.getUserId());
        } else {
            return roleService.deleteByRoleId(userRole.getRoleId(),userRole.getUserId());
        }
    }

}
