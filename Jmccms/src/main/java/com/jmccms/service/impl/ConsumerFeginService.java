package com.jmccms.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 调用oauth2.0的fegin类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.serviceimpl
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-22 22:06
 * @Email chen87647213@163.com
 */
@FeignClient("server-oauth/oauth")
public interface ConsumerFeginService {

    /**
     * https://blog.csdn.net/ityqing/article/details/81217383 swgger2文档
     */

    /**
     * 登录获取token
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/token")
    Object getToken(@RequestParam(value="grant_type")String grant_type, @RequestParam(value="username")String username, @RequestParam(value="password")String password, @RequestParam(value="client_id")String client_id, @RequestParam(value="client_secret")String client_secret, @RequestParam(value="scope")String scope);

    /**
     * 验证token有效性
     * @param token
     * @return
     */
    @RequestMapping("/check_token")
    Object checkToken(@RequestParam(value="token") String token);


    /**
     * 刷新token,即退出注销
     * @param token
     * @return
     */
    @RequestMapping("/token")
    Object refreshToken(@RequestParam(value="grant_type")String grant_type,@RequestParam(value="refresh_token")String refresh_token,@RequestParam(value="client_id")String client_id,@RequestParam(value="client_secret")String client_secret);

}
