package com.cyj.impl;

import com.cxb.cyj.dao.UsersRepository;
import com.cxb.cyj.entity.User;
import com.cxb.cyj.util.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: 自定义用户权限认证
 * @ClassName: MyUserDetailsService.java
 * @author Chenyongjia
 * @Date 2018年12月4日 上午11.23
 * @Email chen867647213@163.com
 */
@Slf4j
@Service
public class CustomUserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsersRepository usersRepository;

    /**
     * 判断用户登录
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("进入判断用户登录方法,"+"收到的账号"+userName);
        User users=usersRepository.findByUserName(userName);
        log.info(users.toString());
        if(IsEmptyUtils.isEmpty(users)){
        	throw new UsernameNotFoundException("Login account does not exist!!!");
        }
        log.info("判断用户登录方法执行结果,"+"用户为:" + users.getUsername() + ";密码为:" + users.getUserPassWord());
        return users;
	}

}
