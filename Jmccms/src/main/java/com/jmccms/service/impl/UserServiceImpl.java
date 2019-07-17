package com.jmccms.service.impl;

import com.jmccms.dao.UserRepository;
import com.jmccms.entity.User;
import com.jmccms.service.UserService;
import com.jmccms.util.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 用户实现
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.serviceimpl
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:24
 * @Email chen87647213@163.com
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据用户名和密码查询用户
     *
     * @param userName
     * @param userPassWord
     * @return
     */
    @Override
    public User findByUserNameAndAndPassWord(String userName, String userPassWord) {
        log.info("进入根据用户名和密码查询用户方法,获取的userName参数为:"+userName+"获取的userPassWord参数为:"+userPassWord);
        return userRepository.findByUserNameAndUserPassWord(userName, userPassWord);
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public User findByUserId(Long userId) {
        log.info("进入根据用户id查询用户信息方法,获取到的userId参数为:"+userId);
        return userRepository.findByUserId(userId);
    }

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    @Override
    public User findByUserName(String userName){
        log.info("进入根据用户名查询用户方法,获取到的userName参数为:"+userName);
        return userRepository.findByUserName(userName);
    }

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        log.info("进入添加用户方法,获取到的User参数为:"+user.toString());
        user.setUserPassWord("cyj123456!");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 加密
        String encodedPassword = passwordEncoder.encode(user.getUserPassWord().trim());
        user.setUserPassWord(encodedPassword);
        user.setUserCreateTime(new Date(System.currentTimeMillis()));
        try {
            User users = userRepository.findByUserName(user.getUserName());
            if (IsEmptyUtils.isEmpty(users)) {
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("添加用户失败", e);
            return false;
        }
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean updUser(User user) {
        log.info("进入修改用户信息方法,获取到的User参数为:"+user.toString());
        try {
            User users = userRepository.findByUserId(user.getUserId());
            if(IsEmptyUtils.isEmpty(users)){
                log.info("修改修改用户信息失败,根据传递的用户userId:"+user.getUserId()+"查询的userInfos参数为空");
                return false;
            }
            // 判断用户姓名是否进行修改
            if (!IsEmptyUtils.isEmpty(user.getUserName())) {
                users.setUserName(user.getUserName());
            }
            // 判断用户密码是否进行修改
            if (!IsEmptyUtils.isEmpty(user.getUserPassWord())) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 加密
                String encodedPassword = passwordEncoder.encode(user.getUserPassWord().trim());
                users.setUserPassWord(encodedPassword);
            }
            // 修改用户登录时间
            users.setUserLastLoginTime(new Date(System.currentTimeMillis()));
            // 修改用户修改人
            users.setUserUpdateMan(user.getUserUpdateMan());

            users.setUserId(user.getUserId());
            userRepository.save(users);
            return true;
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return false;
        }
    }

    /**
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Long> getUserRole(Long userId) {
        log.info("进入根据用户id查询用户角色方法,获取到的userId参数为:"+userId);
        return userRepository.getUserRole(userId);
    }

}
