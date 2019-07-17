package com.jmccms.controller;

import com.jmccms.aspect.lang.annotation.LogRecord;
import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;
import com.jmccms.entity.Permission;
import com.jmccms.service.PermissionService;
import com.jmccms.util.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 权限控制器
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:28
 * @Email chen87647213@163.com
 */
@Slf4j
@RestController
@RequestMapping(value = "/permission",name = "权限模块")
public class PermissionController {

    /**
     * springmvc在启动时候将所有贴有请求映射标签：RequestMapper方法收集起来封装到该对象中
     */
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;// SpringMVC所有控制器中的请求映射集合

    @Autowired
    private PermissionService permissionService;

    /**
     * http://localhost:8066/jmccms/permission/queryNode
     * 查看系统权限
     * @author ChenYongJia
     */
    @LogRecord(title = "查看系统权限", operatetype = OperatorType.SELECT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/queryNode", name = "查看系统权限",method=RequestMethod.GET)
    public Object queryNode() {
        return permissionService.queryNode();
    }

    /**
     * http://localhost:8066/jmccms/permission/updatePermission
     * 更新系统权限信息
     *
     * @author ChenYongJia
     */
    @LogRecord(title = "更新系统权限", operatetype = OperatorType.INSERT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/updatePermission", name = "更新系统权限",method= RequestMethod.POST)
    public String updatePermission() {
        log.info("进去更新系统中所有权限方法...");
        int k=this.updateSysPermission();
        log.info("当前系统中所有权限全部k:{}条更新完毕 ^_^ ",k);
        return "成功更新权限"+k+"条";
    }

    /**
     * 收集系统中所有权限数据更新到数据库
     *
     * * @author ChenYongJia
     */
    @SuppressWarnings("unlikely-arg-type")
    public int updateSysPermission() {

        List<String> ownList = permissionService.queryAll();// 查询出数据库中现有的所有权限数据集合
        Map<RequestMappingInfo, HandlerMethod> requestMap = handlerMapping.getHandlerMethods();// SpringMVC所有控制器中的请求映射集合
        Collection<HandlerMethod> handlerMethods = requestMap.values();// 获取所有controller中所有带有@RequestMapper注解的方法
        if (handlerMethods == null || handlerMethods.size() < 1)
            return 0;// 成功更新0条数据
        List<Permission> pList = new ArrayList<>();// 收集到的待新增的权限集合
        Permission permission;// 待添加的权限对象
        for (HandlerMethod method : handlerMethods) {// 遍历所有带有@RequestMapper注解的方法
            RequestMapping anno = method.getMethodAnnotation(RequestMapping.class);// 从控制器映射方法上取出@RequestMapper注解对象
            // @RequestMapper注解有没有name备注是作为一个权限的标志
            if (!"".equals(anno.name())) {// @RequestMapper注解写了name属性才做权限收集：所以@RequestMapper注解有没有name备注是作为一个权限的标志
                RequestMapping namespaceMapping = method.getBeanType().getAnnotation(RequestMapping.class);// 带有@RequestMapper注解的方法所在控制器类上的RequestMapping注解对象
                String namespace = namespaceMapping.value()[0];// 得到RequestMapping注解的value值,即命名空间,即模块
                String permissionValue = (namespace + ":" + anno.value()[0]).replace("/", "");// 得到权限 ,例如：user:delete
                // 用户模块的删除权限
                if (ownList.contains(permissionValue))
                    continue;// 如果数据库已经存储有这个注解权限,则忽略不收集
                if (pList.contains(permissionValue))
                    continue;// 如果已经收集到该权限,则忽略不再重复收集
                // 构造权限对象,三个参数:权限,模块说明,权限说明
                permission = new Permission(permissionValue, namespaceMapping.name(), anno.name());// 把权限控制注解@Permission解析为权限控制对象SysPermission,方便插入数据库权限表
                pList.add(permission);// 把数据库没有存储的,收集容器中也没有收集到的的权限加入收集容器中.
            }
        }

        if(!IsEmptyUtils.isEmpty(pList.size())) {
            return permissionService.batchInsert(pList);
        }else {
            return 0;
        }
    }

}
