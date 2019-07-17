package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动类
 *
 * @author Chenyongjia
 * @Description: ZuulBackupApplication网关路由启动类
 * @ClassName: Application.java
 * @Date 2018年12月03日 晚上20:29:06
 * @Email 867647213@qq.com
 */
@Slf4j
@EnableZuulProxy
@EnableHystrix
@SpringCloudApplication
public class ZuulBackupApplication {

    public static void main(String[] args) {
        log.info("=======》启动网关路由-Backup节点项目ing......");
        SpringApplication.run(ZuulBackupApplication.class, args);
        log.info("=======》启动网关路由-Backup节点项目成功......");
    }

    //将过滤器用@Component注解代替Spring管理
    /*@Bean
    public ZuulTokenFilter zuulTokenFilter(){
        return new ZuulTokenFilter();
    }*/

}
