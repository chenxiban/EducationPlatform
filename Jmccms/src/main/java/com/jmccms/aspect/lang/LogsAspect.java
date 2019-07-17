package com.jmccms.aspect.lang;

import com.jmccms.aspect.lang.annotation.LogRecord;
import com.jmccms.entity.OperationLog;
import com.jmccms.service.OperationLogService;
import com.jmccms.util.DateUtil;
import com.jmccms.util.IpUtils;
import com.jmccms.util.UserAgentUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description: 日志操作记录
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.aspect.lang
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-04 21:36
 * @Email chen87647213@163.com
 */
@Slf4j
@Aspect
@Component
public class LogsAspect {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.jmccms.aspect.lang.annotation.LogRecord)")
    public void logPointCut() {
        log.info("logEntityPointCut");
    }

    /**
     * 环绕通知 用于拦截操作
     *
     * @param point
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        try {
            result = point.proceed();
            saveLogEntity(point, result);
        } catch (Throwable e) {
            log.error("logPointCut异常", e);
        }

        return result;
    }

    /**
     * @param joinPoint
     */
    protected void saveLogEntity(ProceedingJoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationLog logEntity = new OperationLog();
        //获得用户信息并记录到日志操作日志上
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        logEntity.setLogOperatingCreateTime(DateUtil.getNowDate());
        // 获得注解上的描述并记录到日志操作日志上
        LogRecord logRecord = method.getAnnotation(LogRecord.class);

        // 操作者id先写死
        logEntity.setLogOperator((long) 2);
        // 设置模块
        logEntity.setLogOperatingContent(logRecord.title());
        // 设置操作类型
        logEntity.setLogOperationType(logRecord.operatetype().getReasonPhrase());
        // 设置操作人类别
        //logEntity.setVistortype(logRecord.vistortype().getReasonPhrase());
        // 记录操作人ip地址
        logEntity.setLogOperatorIp(IpUtils.getIpAddr(request));
        // 获得请求的类名
        logEntity.setLogOperatingClassName(joinPoint.getTarget().getClass().getName());
        // 获得请求方法名
        logEntity.setLogOperatingMethodName(signature.getName());
        // 请求路径
        logEntity.setLogOperatingPath(request.getRequestURL().toString());
        // 记录请求方式
        logEntity.setLogOperatingRequestType(request.getMethod());
        // 记录浏览器名称
        logEntity.setLogOperationBrowser(UserAgentUtils.getBrowserName(request));
        // 记录设备名称
        logEntity.setLogOperatingSystem(UserAgentUtils.getSystemName(request));
        // 获得浏览器版本
        logEntity.setLogOperatingBrowserVersion(UserAgentUtils.getBrowserName(request)+"-"+UserAgentUtils.getVersion(request).toString());

        //调用执行目标方法
        HttpServletResponse response = servletRequestAttributes.getResponse();
        // 记录请求结果
        logEntity.setLogOperatingResults("status:" + response.getStatus());
        // 保存操作日志对象
        operationLogService.addOperationLog(logEntity);
    }

}
