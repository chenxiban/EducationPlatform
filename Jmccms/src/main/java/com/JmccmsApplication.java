package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主函数
 * @Description: SpringBoot服务启动类
 * @ClassName: JmccmsApplication.java
 * @author ChenYongJia
 * @Date 2019年05月01日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
/**
 * @SpringCloudApplication等同于( @EnableEurekaClient + @EnableHystrix + @SpringBootApplication)pom.xml必须引Eureka、Hystrix依赖
 */
@EnableFeignClients
@SpringCloudApplication
public class JmccmsApplication {

    public static void main(String[] args) {
        log.info("佳梦财宸管理系统开始启动ing！");
        SpringApplication.run(JmccmsApplication.class, args);
        log.info("佳梦财宸管理系统启动成功ing.......！");
    }

}
