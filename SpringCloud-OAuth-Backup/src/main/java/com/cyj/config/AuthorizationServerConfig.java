package com.cyj.config;

import com.cyj.impl.CustomUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 
 * @Description: 授权认证服务中心配置,启用授权认证中心服务
 * @ClassName: AuthorizationServerConfig.java
 * @author ChenYongJia
 * @Date 2018年12月04日 下午20:40:56
 * @Email 867647213@qq.com
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private CustomUserServiceImpl userDetailsService;

	/*
	 * @Bean public TokenStore tokenStore() { return new JdbcTokenStore(dataSource);
	 * }
	 * 
	 * @Bean // 声明 ClientDetails实现 public ClientDetailsService
	 * clientDetailsService() { return new JdbcClientDetailsService(dataSource); }
	 */

	// accessToken 有效期 2小时
	private static final int ACCESSTOKENVALIDITYSECONDS = 7200 * 12 * 7;// 两小时
	private static final int REFRESHTOKENVALIDITYSECONDS = 7200 * 12 * 7;// 两小时
	// 配置 appid、appkey 、回调地址、token有效期

	/**
	 * configure(ClientDetailsServiceConfigurer clients)
	 * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息；
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory().withClient("client_1").secret(passwordEncoder().encode("123456"))
				.redirectUris("http://www.baidu.com") // 授权码授权模式下的回调地址
				.authorizedGrantTypes("authorization_code", "password", "refresh_token").scopes("all")
				.accessTokenValiditySeconds(ACCESSTOKENVALIDITYSECONDS)
				.refreshTokenValiditySeconds(REFRESHTOKENVALIDITYSECONDS);// 授权类型

	}

	// 设置token类型
	/**
	 * configure(AuthorizationServerEndpointsConfigurer endpoints)
	 * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token
	 * services)，还有token的存储方式(tokenStore)；
	 */
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(authenticationManager()).allowedTokenEndpointRequestMethods(HttpMethod.GET,
				HttpMethod.POST,HttpMethod.PUT,
				HttpMethod.DELETE);
		// 必须加上他，不然刷新令牌接口会报错
		endpoints.authenticationManager(authenticationManager());
		endpoints.userDetailsService(userDetailsService);
	}

	/**
	 * onfigure(AuthorizationServerSecurityConfigurer security) 用来配置令牌端点(Token
	 * Endpoint)的安全约束；
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		// 允许表单认证
		oauthServer.allowFormAuthenticationForClients();
		// 允许check_token访问
		oauthServer.checkTokenAccess("permitAll()");
	}

	/**
	 * 用户自定义
	 */
	/*@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}*/

	/**
	 * 用来做验证
	 * 
	 * @return
	 */
	@Bean
	AuthenticationManager authenticationManager() {
		AuthenticationManager authenticationManager = new AuthenticationManager() {

			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return daoAuhthenticationProvider().authenticate(authentication);
			}
		};
		return authenticationManager;
	}

	// 用来做验证
	@Bean
	public AuthenticationProvider daoAuhthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	// 设置添加用户信息,正常应该从数据库中读取
	/*
	 * @Bean UserDetailsService userDetailsService() { InMemoryUserDetailsManager
	 * userDetailsService = new InMemoryUserDetailsManager();
	 * userDetailsService.createUser(User.withUsername("user_1").password(
	 * passwordEncoder().encode("123456")) .authorities("ROLE_USER",
	 * "deleteOrder").build());
	 * userDetailsService.createUser(User.withUsername("user_2").password(
	 * passwordEncoder().encode("1234567")) .authorities("ROLE_USER",
	 * "updateOrder").build()); return userDetailsService; }
	 */

	@Bean
	PasswordEncoder passwordEncoder() {
		// 加密方式,自行百度---BCryptPasswordEncoder
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

}
