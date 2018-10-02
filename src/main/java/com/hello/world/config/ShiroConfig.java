package com.hello.world.config;

import com.hello.world.auth.AuthRealm;
import com.hello.world.auth.JWTFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Slf4j
@Configuration
public class ShiroConfig {
  /**
   * 自定义Realm
   *
   * @return authRealm
   */
  @Bean
  public AuthRealm realm() {
    AuthRealm authRealm = new AuthRealm();

    return authRealm;
  }

  /**
   * Shiro Filter
   *
   * @param securityManager securityManager
   * @return shiroFilterFactoryBean
   */
  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    log.info("======== Shiro config ==========");

    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);

    // jwt过滤器
    Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
    filterMap.put("jwt", new JWTFilter());
    shiroFilterFactoryBean.setFilters(filterMap);

    shiroFilterFactoryBean.setUnauthorizedUrl("/401");

    // 拦截器
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

    // 访问401、404页面不通过Filter
    filterChainDefinitionMap.put("/401", "anon");
    filterChainDefinitionMap.put("/404", "anon");

    // Login
    filterChainDefinitionMap.put("/verifyCode", "anon");
    filterChainDefinitionMap.put("/webLogin", "anon");

    filterChainDefinitionMap.put("/**", "jwt");

    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return shiroFilterFactoryBean;
  }

  /**
   * Security Manager
   *
   * @return securityManager
   */
  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

    // 设置Realm
    securityManager.setRealm(realm());

    // 注入缓存管理器
    securityManager.setCacheManager(ehCacheManager());

    // 关闭Shiro自带的session
    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
    defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
    securityManager.setSubjectDAO(subjectDAO);

    return securityManager;
  }

  /**
   * 开启Shiro aop注解支持, 使用代理方式; 所以需要开启代码支持;
   *
   * @param securityManager 安全管理器
   * @return 授权Advisor
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);

    return advisor;
  }

  /**
   * Shiro 生命周期管理
   * 注解使用的Bean, 没有则Shiro的注解不生效
   *
   * @return lifecycleBeanPostProcessor
   */
  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  /**
   * DefaultAdvisorAutoProxyCreator, Spring的一个bean, 由Advisor决定对哪些类的方法进行AOP代理
   * 注解使用的Bean, 没有则Shiro的注解不生效
   *
   * @return proxyCreator
   */
  @Bean
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
    proxyCreator.setProxyTargetClass(true);

    return proxyCreator;
  }

  /**
   * Shiro缓存管理器
   * 需要注入对应的其它实体类中
   *
   * @return ehCacheManager
   */
  @Bean
  public EhCacheManager ehCacheManager() {
    EhCacheManager ehCacheManager = new EhCacheManager();
    ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");

    return ehCacheManager;
  }
}
