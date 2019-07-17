
package com.jmccms.config;

//import com.jmccms.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


/**
 * @Description: 配置SpringSecurityConfig权限拦截
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.config
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 22:31
 * @Email chen87647213@163.com
 */


//https://www.cnblogs.com/dailiang1993/p/7803550.html
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private AccessDeniedHandler accessDeniedHandler;
    Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor*/


/**
     * 数据库取出用户数据
     * @return
     */

   /* @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new CustomUserService();
    }*/


/**
     * 进行auth权限验证
     * @param auth
     * @throws Exception
     */

   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(customUserService()); //user Details Service验证
    }

    */
/**
     * 配置拦截内容
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*/**").permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll().
                and()
                .cors()//新加入
                .and()
                .csrf().disable();; //注销行为任意访问
        //http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }


/**
     *
     * @param web
     * @throws Exception
     */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favor.ioc");
    }

}
