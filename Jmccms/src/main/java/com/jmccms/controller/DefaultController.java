package com.jmccms.controller;

import com.jmccms.aspect.lang.annotation.LogRecord;
import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 页面跳转控制器
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:29
 * @Email chen87647213@163.com
 */
@Controller
public class DefaultController {

    /**
     * http://localhost:8066/jmccms/login 跳转登录页面
     * @return
     */
    @LogRecord(title = "跳转登录页面", operatetype = OperatorType.JUMP, vistortype = BusinessType.MANAGE)
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * http://localhost:8066/jmccms/main 跳转应用页面
     * @return
     */
    @LogRecord(title = "跳转应用页面", operatetype = OperatorType.JUMP, vistortype = BusinessType.MANAGE)
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    /**
     * http://localhost:8066/jmccms/modules 跳转菜单模块页面
     * @return
     */
    @LogRecord(title = "跳转菜单模块页面", operatetype = OperatorType.JUMP, vistortype = BusinessType.MANAGE)
    @GetMapping("/modules")
    public String modules() {
        return "modules";
    }

    /**
     * http://localhost:8066/jmccms/permission 跳转权限页面
     * @return
     */
    @LogRecord(title = "跳转权限页面", operatetype = OperatorType.JUMP, vistortype = BusinessType.MANAGE)
    @GetMapping("/permission")
    public String permission() {
        return "permission";
    }

    /**
     * http://localhost:8066/jmccms/userInfo 跳转用户信息页面
     * @return
     */
    @LogRecord(title = "跳转用户信息页面", operatetype = OperatorType.JUMP, vistortype = BusinessType.MANAGE)
    @GetMapping("/userInfo")
    public String userInfo() {
        return "userInfo";
    }

    /**
     * http://localhost:8066/jmccms/role 跳转角色页面
     * @return
     */
    @LogRecord(title = "跳转角色页面", operatetype = OperatorType.JUMP, vistortype = BusinessType.MANAGE)
    @GetMapping("/role")
    public String role() {
        return "role";
    }

    /**
     * http://localhost:8066/jmccms/error403 跳转错误页面
     * @return
     */
    @LogRecord(title = "跳转错误页面", operatetype = OperatorType.JUMP, vistortype = BusinessType.MANAGE)
    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
