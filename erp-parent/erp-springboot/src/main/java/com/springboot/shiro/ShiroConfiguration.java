/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springboot.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authz.SslFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.DelegatingFilterProxy;

import redragon.util.i18n.i18n;

import com.framework.shiro.MyCasRealm;
import com.framework.shiro.filter.AnyPermsFilter;
import com.framework.shiro.filter.AnyRolesFilter;
import com.framework.shiro.session.MyRedisSessionDao;
import com.framework.shiro.session.MyWebSessionManager;
import com.framework.spring.QuartzSessionValidationScheduler;

/**
 * Shiro的主配置类
 * @author dongbin
 */
@Configuration
public class ShiroConfiguration {
	
    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm， 负责用户的认证和授权
     */
    @Bean(name="casRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public MyCasRealm casRealm() {
    	MyCasRealm myCasRealm = new MyCasRealm();
    	myCasRealm.setCachingEnabled(true);
    	myCasRealm.setAuthenticationCachingEnabled(true);
    	myCasRealm.setAuthenticationCacheName("authenticationCache");
    	myCasRealm.setAuthorizationCachingEnabled(true);
    	myCasRealm.setAuthorizationCacheName("authorizationCache");
    	//CAS Server服务器端地址
    	myCasRealm.setCasServerUrlPrefix(i18n.getKeyValue("shiro", "casUrl"));
    	//当前应用CAS服务URL，即用于接收并处理登录成功后的Ticket的
    	myCasRealm.setCasService(i18n.getKeyValue("shiro", "requestUrl"));
    	
        return myCasRealm;
    }
    
    /**
     * Shiro的缓存管理器
     */
    @Bean(name="shiroCacheManager")
    public EhCacheManager shiroCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:conf/ehcache/ehcache_shiro.xml");
        return ehCacheManager;
    }
    
    /**
     * 会话ID生成器
     */
    @Bean(name="sessionIdGenerator")
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
    	return new JavaUuidSessionIdGenerator();
    }
    
    /**
     * 用于管理所有shiro session
     */
    @Bean(name="sessionDAO")
    public MyRedisSessionDao sessionDAO() {
    	MyRedisSessionDao sessionDAO = new MyRedisSessionDao();
    	sessionDAO.setSessionIdGenerator(this.sessionIdGenerator());
    	//设置获取cookie的key
    	sessionDAO.setCookieKey("sid");
    	//设置redis中存储session的有效期
    	sessionDAO.setRedisSessionTimeout(1800);
    	
    	return sessionDAO;
    }
    
    /**
     * 将会话存储在Cookie中
     */
    @Bean(name="sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
    	//cookie的存储session的key
    	SimpleCookie simpleCookie = new SimpleCookie("sid");
    	simpleCookie.setHttpOnly(true);
    	//cookie关闭浏览器即失效
    	simpleCookie.setMaxAge(-1);
    	//设置cookie的共享的域名
    	simpleCookie.setDomain(i18n.getKeyValue("shiro", "cookieDomain"));
    	
    	return simpleCookie;
    }
    
    /**
     * rememberMe 120天
     */
    @Bean(name="rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(120);
        //设置cookie的共享的域名
        simpleCookie.setDomain(i18n.getKeyValue("shiro", "cookieDomain"));
        return simpleCookie;
    }
    
    /**
     * rememberMe管理器
     * rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位）
     */
    @Bean(name="rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCookie(this.rememberMeCookie());
        return cookieRememberMeManager;
    }
    
    /**
     * 会话管理器
     */
    @Bean(name="sessionManager")
    public MyWebSessionManager sessionManager() {
    	MyWebSessionManager sessionManager = new MyWebSessionManager();
    	sessionManager.setGlobalSessionTimeout(1800000);
    	sessionManager.setDeleteInvalidSessions(true);
    	//session的验证器
    	sessionManager.setSessionValidationSchedulerEnabled(true);
    	sessionManager.setSessionValidationScheduler(this.sessionValidationScheduler(sessionManager));
    	//设置session管理器
    	sessionManager.setSessionDAO(this.sessionDAO());
    	sessionManager.setSessionIdCookieEnabled(true);
    	//设置session的cookie管理器
    	sessionManager.setSessionIdCookie(this.sessionIdCookie());
    	
    	return sessionManager;
    }
    
    /**
     * 会话验证调度器
     */
    @Bean(name="sessionValidationScheduler")
    public QuartzSessionValidationScheduler sessionValidationScheduler(ValidatingSessionManager sessionManager) {
    	QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
    	sessionValidationScheduler.setSessionValidationInterval(1800000);
    	sessionValidationScheduler.setSessionManager(sessionManager);
    	
    	return sessionValidationScheduler;
    }
    
    /**
     * cas remember me 功能
     */
    @Bean(name="casSubjectFactory")
    public CasSubjectFactory casSubjectFactory() {
    	return new CasSubjectFactory();
    }

    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁
     */
    @Bean(name="lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    
    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.casRealm());
        //基于ehCache来缓存用户认证信息和授权信息的实现
        securityManager.setCacheManager(this.shiroCacheManager());
        //sessionMode参数设置为native时，那么shrio就将用户的基本认证信息保存到缺省名称为shiro-activeSessionCache 的Cache中
        securityManager.setSessionMode("native");
        securityManager.setSessionManager(this.sessionManager());
        securityManager.setRememberMeManager(this.rememberMeManager());
        securityManager.setSubjectFactory(this.casSubjectFactory());
        
        return securityManager;
    }
    
    /**
     * Cas Filter
     * 用于拦截cas的回调验证
     */
    @Bean(name="casFilter")
    public CasFilter casFilter() {
    	CasFilter casFilter = new CasFilter();
    	//bbc
    	casFilter.setFailureUrl("/error");
    	return casFilter;
    }
    
    /**
     * Shiro SSL filter
     * 用于强制转换ssl访问
     */
    @Bean(name="sslFilter")
    public SslFilter sslFilter() {
    	SslFilter sslFilter = new SslFilter();
    	sslFilter.setPort(443);
    	
    	return sslFilter;
    }
    
    /**
     * logout filter
     * 用于退出系统
     */
//    @Bean(name="logout")
    public LogoutFilter logoutFilter() {
    	LogoutFilter logoutFilter = new LogoutFilter();
    	logoutFilter.setRedirectUrl(i18n.getKeyValue("shiro", "casUrl")+"/logout?service="+i18n.getKeyValue("shiro", "successUrl"));
    	
    	return logoutFilter;
    }
    
    /**
     * anyRoles filter
     * 用于角色的过滤
     */
    @Bean(name="anyRolesFilter")
    public AnyRolesFilter anyRolesFilter() {
    	AnyRolesFilter anyRolesFilter = new AnyRolesFilter();
    	anyRolesFilter.setLoginUrl(i18n.getKeyValue("shiro", "casUrl")+"/login?service="+i18n.getKeyValue("shiro", "requestUrl"));
    	anyRolesFilter.setUnauthorizedUrl(i18n.getKeyValue("shiro", "unauthUrl"));
    	
    	return anyRolesFilter;
    }
    
    /**
     * anyPerms filter
     * 用于权限的过滤
     */
    @Bean(name="anyPermsFilter")
    public AnyPermsFilter anyPermsFilter() {
    	AnyPermsFilter anyPermsFilter = new AnyPermsFilter();
    	anyPermsFilter.setLoginUrl(i18n.getKeyValue("shiro", "casUrl")+"/login?service="+i18n.getKeyValue("shiro", "requestUrl"));
    	anyPermsFilter.setUnauthorizedUrl(i18n.getKeyValue("shiro", "unauthUrl"));
    	return anyPermsFilter;
    }
    
    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置SecuritManager
        shiroFilterFactoryBean.setSecurityManager(this.securityManager());
        
        // 如果不设置默认会自动寻找工程根目录下的"/login"页面
        shiroFilterFactoryBean.setLoginUrl(i18n.getKeyValue("shiro", "casUrl")+"/login?service="+i18n.getKeyValue("shiro", "requestUrl"));
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl(i18n.getKeyValue("shiro", "successUrl"));
        // 未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl(i18n.getKeyValue("shiro", "unauthUrl"));
        
        // 自定义拦截器
        Map<String, Filter> filtersMap = shiroFilterFactoryBean.getFilters();
        filtersMap.put("cas", this.casFilter());
        filtersMap.put("ssl", this.sslFilter());
        filtersMap.put("logout", this.logoutFilter());
        filtersMap.put("anyRoles", this.anyRolesFilter());
        filtersMap.put("anyPerms", this.anyPermsFilter());
        
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/casclient", "cas");
        // 配置退出过滤器,其中的具体代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //authc anon
        filterChainDefinitionMap.put("/**", "authc");
        // 加载shiroFilter权限控制规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;

    }
    
    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     * 老实说，这里注入securityManager，我不知道有啥用，从source上看不出它在什么地方会被调用。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(this.securityManager());
        return aasa;
    }

    /**  
     * 注册DelegatingFilterProxy（Shiro）  
     */  
    @Bean  
    public FilterRegistrationBean delegatingFilterProxy() {  
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();  
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));  
        //该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");  
        filterRegistration.setEnabled(true);  
        filterRegistration.addUrlPatterns("/*");  
        return filterRegistration;  
    }  
    
}
