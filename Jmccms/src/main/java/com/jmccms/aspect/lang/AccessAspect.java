package com.jmccms.aspect.lang;

import com.jmccms.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Description: 定义切面类，实现web层的访问记录切面
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.comment.log
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-03 22:16
 * @Email chen87647213@163.com
 */
@Slf4j
@Aspect
@Component
public class AccessAspect {

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution( * com.jmccms.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 定义切入点，切入点为com.jmccms.controller 下的所有函数
     * 前置通知：在连接点之前执行的通知
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) { // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest(); // 记录下请求内容

        log.info("请求的URL : {},请求方式HTTP_METHOD: {},请求地址IP: {},请求的类方法CLASS_METHOD: {},请求结果ARGS: {}", request.getRequestURL().toString(), request.getMethod(), IpUtils.getIpAddr(request), joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        //log.info("HTTP_METHOD : " + request.getMethod());
        //log.info("IP : " + IpUtils.getIpAddr(request));
        //下面这个getSignature().getDeclaringTypeName()是获取包+类名的   然后后面的joinPoint.getSignature.getName()获取了方法名
        //log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        log.info("需要加强的目标类的对象TARGET:{},经过加强后的代理类的对象THIS:{}",joinPoint.getTarget(),joinPoint.getThis());//返回的是需要加强的目标类的对象
    }

}
