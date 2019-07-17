package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 启动类
 *
 * @author Chenyongjia
 * @Description: ZuulMasterApplication
 * @ClassName: Application.java
 * @Date 2018年12月03日 晚上20:29:06
 * @Email 867647213@qq.com
 */
@Slf4j
@EnableZuulProxy
@EnableHystrix
@SpringCloudApplication
public class ZuulMasterApplication {

    public static void main(String[] args) {
        log.info("=======》启动网关路由-Master节点项目ing......");
        SpringApplication.run(ZuulMasterApplication.class, args);
        log.info("=======》启动网关路由-Master节点项目成功......");
    }

    /**
     *
     * attention:简单跨域就是GET，HEAD和POST请求，但是POST请求的"Content-Type"只能是application/x-www-form-urlencoded,
     * multipart/form-data 或 text/plain
     * 反之，就是非简单跨域，此跨域有一个预检机制，说直白点，就是会发两次请求，一次OPTIONS请求，一次真正的请求
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(18000L);
        config.addAllowedMethod("OPTIONS");// 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod("GET");// 允许Get的请求方法
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
