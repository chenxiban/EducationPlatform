package com.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: 配置类禁用csrf
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.config
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-18 19:01
 * @Email chen87647213@163.com
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

}
