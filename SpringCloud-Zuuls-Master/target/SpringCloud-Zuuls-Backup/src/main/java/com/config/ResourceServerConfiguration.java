package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Description: 网关路由服务配置
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.config
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-18 17:17
 * @Email chen87647213@163.com
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * @EnableResourceServer 开启资源服务中心, 一般配置在网关.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
		/*http.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and().authorizeRequests().anyRequest().authenticated().and().httpBasic();*/
        // 对 api/order 请求进行拦截 验证 accessToken
        http.authorizeRequests().antMatchers("/api/order/**").authenticated()
                .and().headers().frameOptions().disable();
    }

}

