package com.jmccms.controller;

import com.jmccms.aspect.lang.annotation.LogRecord;
import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;
import com.jmccms.service.ModulesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 模块控制器
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:27
 * @Email chen87647213@163.com
 */
@Slf4j
@RestController
@RequestMapping(value = "/modules", name = "菜单模块")
public class ModulesController {

    @Autowired
    private ModulesService modulesService;

    /**
     * 显示所有模块 http://localhost:8066/jmccms/modules/getModules
     *
     * @return
     */
    @LogRecord(title = "显示所有模块", operatetype = OperatorType.SELECT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/getModules", name = "显示所有模块", method = RequestMethod.GET)
    public Object getModules() {
        log.info("进入显示所有模块方法控制器");
        return modulesService.showModules();
    }

}
