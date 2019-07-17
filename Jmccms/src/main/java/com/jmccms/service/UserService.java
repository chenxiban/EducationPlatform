package com.jmccms.service;

import com.jmccms.entity.User;

import java.util.List;

/**
 * @Description: 用户业务层
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:15
 * @Email chen87647213@163.com
 */
public interface UserService {

    /**
     * 根据用户名和密码查询用户
     * @param userName
     * @param userPassWord
     * @return
     */
    User findByUserNameAndAndPassWord(String userName, String userPassWord);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    User findByUserId(Long userId);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    boolean updUser(User user);

    /**
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    List<Long> getUserRole(Long userId);

}
