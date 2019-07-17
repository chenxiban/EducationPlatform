package com.jmccms.intercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 允许任何域名使用 允许任何头 允许任何方法（post、get等）
 *
 * @author ChenYongJia
 * @Description:允许任何域名使用 允许任何头 允许任何方法（post、get等）
 * @ClassName: CorsConfig.java
 * @Date 2018年12月02日 晚上22：54
 * @Email chen87647213@163.com
 */
@Configuration
public class GlobalCorsConfig extends WebMvcConfigurerAdapter {
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" ,"OPTIONS"};
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods(ORIGINS)
                .maxAge(3600);
    }

}
