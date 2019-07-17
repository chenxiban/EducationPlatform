package com.jmccms.service.impl;

import com.jmccms.dao.ModulesRepository;
import com.jmccms.entity.Modules;
import com.jmccms.service.ModulesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 模块实现
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.serviceimpl
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:19
 * @Email chen87647213@163.com
 */
@Slf4j
@Service
public class ModulesServiceImpl implements ModulesService {

    @Autowired
    private ModulesRepository modulesRepository;

    /**
     * 查询所有Modules模块信息
     */
    @Override
    public List<Modules> showModules() {
        log.info("进入查询所有Modules模块信息方法");
        List<Modules> rootList = null;
        try {
            // 查询出所有根菜单
            rootList = modulesRepository.queryChildren(0);
            // 递归设置子菜单
            this.setChildrens(rootList);
            return rootList;
        } catch (Exception e) {
            log.error("调用查询所有Modules模块信息方法失败",e);
            return null;
        }

    }

    /**
     * 给菜单设置子菜单
     *
     * @param parentList
     */
    private void setChildrens(List<Modules> parentList) {
        log.info("进入给菜单设置子菜单方法,获取到的参数为parentList:{}",parentList);
        for (Modules m : parentList) {
            // 查询出子菜单
            List<Modules> childrenList = this.queryChildren(m.getModulesId());
            // 如果没有子菜单则递归结束
            if (childrenList != null && !childrenList.isEmpty()) {// 有子菜单
                // 设置子菜单
                m.setChildren(childrenList);
                // 如果有子菜单则继续递归设置子菜单
                this.setChildrens(childrenList);
            }
        }
    }

    /**
     * 查询孩子菜单
     *
     * @param parentId
     * @return
     */
    public List<Modules> queryChildren(Integer parentId) {
        log.info("进入查询孩子菜单方法,获取到的参数为parentId:{}",parentId);
        try {
            return modulesRepository.queryChildren(parentId);
        } catch (NullPointerException e) {
            log.error("调用查询孩子菜单方法失败",e);
            return null;
        }
    }

}
