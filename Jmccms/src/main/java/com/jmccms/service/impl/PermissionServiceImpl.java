package com.jmccms.service.impl;

import com.jmccms.dao.PermissionRepository;
import com.jmccms.entity.Modules;
import com.jmccms.entity.Permission;
import com.jmccms.service.PermissionService;
import com.jmccms.util.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 权限实现
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.serviceimpl
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:20
 * @Email chen87647213@163.com
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;


    /**
     * 查询所有权限集合树形展示
     * @return 权限字符串集合
     * @author Chenyongjia
     */
    @Override
    public List<Permission> queryNode(){
        log.info("进入查询所有权限集合树形展示方法");
        List<Permission> permissionTree = permissionRepository.findsGroup();// 查询出所有的权限树
        List<Permission> nodes=new ArrayList<>();
        if(!IsEmptyUtils.isEmpty(permissionTree)){
            for (int i = 0; i < permissionTree.size(); i++) {
                Permission node=new Permission();
                node.setPermissionId(permissionTree.get(i).getPermissionId());
                node.setPermissionCreateTime(permissionTree.get(i).getPermissionCreateTime());
                node.setPermissionValue(permissionTree.get(i).getPermissionValue());
                node.setLabel(permissionTree.get(i).getPermissionModule());
                // 为node对象设置children属性
                node.setChildren(this.findsByPermissionModules(permissionTree.get(i).getPermissionModule()));
                nodes.add(node);
            }
        }
        return nodes;

    }



    /**
     * 给菜单设置子菜单
     *
     * @param permissionModule
     */
    private List<Permission> findsByPermissionModules(String permissionModule) {
        log.info("进入给菜单设置子菜单方法,获取到的参数为permissionModule:{}",permissionModule);
        List<Permission>  objectsList = permissionRepository.findsByPermissionModule(permissionModule);
        if(!IsEmptyUtils.isEmpty(objectsList)){
            for (int i = 0; i < objectsList.size(); i++) {
                objectsList.get(i).setId(objectsList.get(i).getPermissionId());
                objectsList.get(i).setLabel(objectsList.get(i).getPermissionName());
                objectsList.get(i).setPermissionLastUpdateTime(objectsList.get(i).getPermissionLastUpdateTime());
                objectsList.get(i).setPermissionCreateTime(objectsList.get(i).getPermissionCreateTime());
                objectsList.get(i).setPermissionValue(objectsList.get(i).getPermissionValue());
                objectsList.get(i).setPermissionId(objectsList.get(i).getPermissionId());
            }
        }
        return objectsList;
    }


    /**
     * 查询所有权限集合
     *
     * @return 权限字符串集合
     * @author Chenyongjia
     */
    @Override
    public List<String> queryAll() {
        log.info("进入查询所有权限集合方法");
        List<String> pList = new ArrayList<>();
        List<Permission> list = permissionRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            pList.add(list.get(i).getPermissionValue());
        }
        return pList;

    }

    /**
     * 批量插入权限数据
     *
     * @param pList
     * @return 成功插入的权限数据条数
     * @author Chenyongjia
     */
    @Override
    public Integer batchInsert(List<Permission> pList) {
        log.info("进入批量插入权限数据方法pList:{}", pList);
        Integer num = 0;
        for (int i = 0; i < pList.size(); i++) {
            Permission entity = pList.get(i);
            entity.setPermissionCreateTime(new Date());
            if (!IsEmptyUtils.isEmpty(permissionRepository.save(entity))) {
                num += 1;
            }
        }
        return num;

    }

}
