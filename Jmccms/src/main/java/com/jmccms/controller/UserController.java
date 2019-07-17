package com.jmccms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户控制器
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:31
 * @Email chen87647213@163.com
 */
@Slf4j
@RestController
@RequestMapping(value = "/user",name = "用户模块")
public class UserController {

    /*@Autowired
    private UserService userService;*/

    /**
     * 修改用户 http://localhost:8066/jmccms/user/updUser
     * @param user
     * @return
     */
    /*@RequestMapping(value = "/updUser", name = "修改用户密码", method = RequestMethod.PUT)
    public boolean updUser(@RequestBody User user){
        log.info("进入修改用户密码方法");
        return userService.updUser(user);
    }*/

}
