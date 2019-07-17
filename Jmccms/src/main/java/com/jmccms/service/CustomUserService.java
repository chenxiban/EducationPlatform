package com.jmccms.service;/*
package com.jmccms.service;

import com.jmccms.entity.Role;
import com.jmccms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @Description: 用于将用户权限交给 springsecurity 进行管控
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 23:26
 * @Email chen87647213@163.com
 *//*

@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    private UserService userDao;

    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        User user = userDao.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(Role role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println(role.getRoleName());
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassWord(), authorities);
    }

}
*/
