package com.jmccms.controller;

import com.jmccms.entity.Result;
import com.jmccms.entity.User;
import com.jmccms.entity.UserInfo;
import com.jmccms.service.UserInfoService;
import com.jmccms.service.UserService;
import com.jmccms.service.impl.ConsumerFeginService;
import com.jmccms.util.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 登录控制器
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:29
 * @Email chen87647213@163.com
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private ConsumerFeginService consumerFeginService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用于oauth2.0的密码认证模式的参数
     *
     * @author chenyongjia
     */
    private String grant_type = "";
    private final String client_id = "client_1";
    private final String client_secret = "123456";
    private final String scope = "all";

    /**
     * http://localhost:3010/serve-zuul/jmccms//oauth/token?username=user_1&password=123456
     * name:登录获取Token
     * @param username
     * @param password
     * @author ChenYongJia
     * @return
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.GET)
    public Object getToken(String username, String password) {
        log.info("进入登录获取Token方法,获取到的参数username为:"+username+",参数password为:"+password);
        grant_type = "password";

        if (IsEmptyUtils.isEmpty(username)) {
            return new Result(false, "用户名不能为空!");
        } else if (IsEmptyUtils.isEmpty(password)) {
            return new Result(false, "用户密码不能为空!");
        }

        User user = userService.findByUserName(username);
        UserInfo userInfo= userInfoService.findByUserInfoName(username);
        if (IsEmptyUtils.isEmpty(user) && IsEmptyUtils.isEmpty(userInfo)) {
            return new Result(false, "当前用户不存在");
        } else {
            User user2=new User();
            user2.setUserName(username);
            user2.setUserPassWord(password);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 加密
            if (passwordEncoder.matches(password, user.getUserPassWord())) {
                int p=userInfo.getUserInfoPsdWrongTime();
                if (p<3) {
                    userInfo.setUserInfoPsdWrongTime(0);
                    userInfo.setUserInfoLastUpdateTime(new Date());
                    userService.addUser(user);
                    userInfoService.addUserInfo(userInfo);
                    Object object = consumerFeginService.getToken(grant_type, username, password, client_id, client_secret, scope);
                    if (IsEmptyUtils.isEmpty(object)) {
                        return new Result(false, "登录失败，请重试");
                    } else {
                        // 根据用户Id查询出该用户的所有权限
                        /*List<String> permissionValueList = userService
                                .queryPermissionValueByUserId(user.getUserId());*/
                        List<Long> urRoles=userService.getUserRole(user.getUserId());
                        Map<String, Object> map = new HashMap<>();
                        map.put("object", object);
                        map.put("roleIds", urRoles);
                        //map.put("permission", permissionValueList);
                        return new Result(true, map);
                    }
                } else {
                    return new Result(false, "当前用户已被锁定请联系管理员");
                }
            }else {
                int p=userInfo.getUserInfoPsdWrongTime();
                if (p<3) {
                    if (p == 3) {
                        userInfo.setUserInfoPsdWrongTime(p+1);
                        userInfo.setUserInfoIsLookout("是");
                        userInfo.setUserInfoLockTime(new Date());
                        userService.addUser(user);
                    } else {
                        userInfo.setUserInfoPsdWrongTime(p+1);
                        userService.addUser(user);
                        userInfoService.addUserInfo(userInfo);
                    }
                } else {
                    return new Result(false, "当前用户已被锁定请联系管理员");
                }
                return new Result(false, "用户密码输入错误");
            }
        }

    }

    /**
     * http://localhost:3010/serve-zuul/jmccms/oauth/check_token?token=a18b696b-4943-483a-8e16-f80885b3c2df
     * name:验证token是否有效
     * @param token
     * @author ChenYongJia
     * @return
     */
    @RequestMapping(value = "/oauth/check_token", method = RequestMethod.GET)
    public Object checkToken(@RequestParam(value = "token") String token) {
        log.info("进入验证用户token有效性方法,获取到的参数token为:"+token);
        try {
            Object object = consumerFeginService.checkToken(token);
            log.info("验证token是否有效方法执行成功");
            return new Result(true, object);
        } catch (Exception e) {
            log.info("验证token是否有效方法执行失败");
            return new Result(false, "当前token无效，请登录重试！！！");
        }
    }

    /**
     * http://localhost:3010/serve-zuul/jmccms/oauth/refresh_Token?token=token
     * name:注销用户token方法
     * @param token
     * @author ChenYongJia
     * @return
     */
    @RequestMapping(value = "/oauth/refresh_Token", method = RequestMethod.GET)
    public boolean refreshToken(@RequestParam(value = "token") String token) {
        log.info("进入注销用户token方法,获取到的参数token为:"+token);
        grant_type = "refresh_token";
        if (!IsEmptyUtils.isEmpty(consumerFeginService.refreshToken(grant_type, token, client_id, client_secret))) {
            log.info("注销用户token方法执行成功");
            return true;
        } else {
            log.info("注销用户token方法执行失败");
            return false;
        }
    }


}
