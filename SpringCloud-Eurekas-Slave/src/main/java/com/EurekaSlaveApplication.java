package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ChenYongJia
 * @Description: EurekaSlaveApplication服务注册中心集群配置节点
 * @ClassName: SpringCloudApplication.java
 * @Date 2018年12月01日 晚上22：54
 * @Email chen87647213@163.com
 */
@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class EurekaSlaveApplication {

    public static void main(String[] args) {
        log.info("=======》启动服务注册中心--Slave服务项目ing......");
        SpringApplication.run(EurekaSlaveApplication.class, args);
        log.info("=======》启动服务注册中心--Slave服务项目成功......");
    }

}
