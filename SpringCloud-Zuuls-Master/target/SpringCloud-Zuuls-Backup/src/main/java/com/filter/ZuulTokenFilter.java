package com.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

/**
 * @Description: Zuul网关路由拦截器配置
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.filter
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-18 17:19
 * @Email chen87647213@163.com
 * 功能描述: 自定义一个Zuul Filter,它在请求路由之前进行过滤
 */
@Slf4j
@Component
public class ZuulTokenFilter extends ZuulFilter {

//    private static Logger log = LoggerFactory.getLogger(ZuulTokenFilter.class);

    @Value("${server.port}")
    private String serverPort;

    /**
     * 过滤器的类型,它决定过滤器在请求的哪个生命周期中执行,
     * pre:请求被路由之前做一些前置工作 ,比如请求和校验
     * routing : 在路由请求时被调用,路由请求转发,即是将请求转发到具体的服务实例上去.
     * post : 在routing 和 error过滤器之后被调用..所以post类型的过滤器可以对请求结果进行一些加工
     * error :处理请求发生错误时调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     *过滤器的执行顺序.
     *在一个阶段有多个过滤器时,需要用此指定过滤顺序
     * 数值越小优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否执行,直接返回true,代表对所有请求过滤
     * 此方法指定过滤范围
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。 这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
     * 然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，
     * 当然我们也可以进一步优化我们的返回，比如，通过ctx.getResponse()对响应内容进行编辑等。
     */
    @Override
    public Object run() {
        // 1.获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 2.获取 Request
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        // 3.获取token 的时候 从请求头中获取

        Object accessToken = request.getParameter("token");
        request.setAttribute("serverPort", serverPort);
		/*if (accessToken != null) {
			return null;// 网关路由放行
		}
		log.warn("token is empty");
		ctx.setSendZuulResponse(false);// 网关路由过滤不路由
		ctx.setResponseStatusCode(401);
		try {
			// 令牌是空的! ! !请先登录! ! !
			ctx.getResponse().getWriter().write("Token is empty ！！！Please log in first ！！！");
		} catch (Exception e) {

		}
		ctx.getResponse().setHeader("x-frame-options","SAMEORIGIN");*/
        // 正常执行调用其他服务接口...
        System.out.println("网关执行端口号:" + serverPort);
        return null;// 网关路由放行

    }

}

