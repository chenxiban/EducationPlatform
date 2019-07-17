package com.jmccms.service;

import com.jmccms.entity.Modules;

import java.util.List;

/**
 * @Description: 模块业务层
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:07
 * @Email chen87647213@163.com
 */
public interface ModulesService {

    /**
     * 显示所有菜单
     * @return
     */
    List<Modules> showModules();

}
