package com.cyj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author ChenYongJia 2018年12月5日
 */

/**
 *
 * @Description: SpringCloud微服务启动类
 * @ClassName: OAuth2BackUpApplication启动类
 * @author ChenYongJia
 * @Date 2018年12月01日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
@SpringCloudApplication // ===( @EnableEurekaClient + @EnableHystrix + @SpringBootApplication
// )pom.xml必须引Eureka、Hystrix依赖
@EnableFeignClients
@EnableAuthorizationServer
public class OAuth2BackUpApplication {

    public static void main(String[] args) {
        log.info("=======》启动OAuth2.0权限认证中心-BackUp节点项目ing......");
        SpringApplication.run(OAuth2BackUpApplication.class, args);
        log.info("=======》启动OAuth2.0权限认证中心-BackUp节点项目成功......");
    }

}
