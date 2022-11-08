package com.ty_home_backend.shiro;

import lombok.var;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public MyShiroRealm myShiroRealm() {
        return new MyShiroRealm();
    }
    @Bean
    public SecurityManager securityManager() {
        var defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myShiroRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        var factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap=new HashMap<>();
        var jwtFilter=new JWTFilter();
        filterMap.put("jwt",jwtFilter);
        factoryBean.setFilters(filterMap);

        Map<String,String> filterRuleMap=new HashMap<>();
//        filterRuleMap.put("/account/register", "anon");
//        filterRuleMap.put("/account/login", "anon");
//        filterRuleMap.put("/account/test","jwt");
//        filterRuleMap.put("/pic","jwt");
//        filterRuleMap.put("/pic/testGetPic","anon");
//        filterRuleMap.put("/file","jwt");
//        filterRuleMap.put("/team/getTeamBrief","anon");
//        filterRuleMap.put("/team/**","jwt");
//        filterRuleMap.put("/team/member","jwt");
//        filterRuleMap.put("/team/member/**","jwt");
//        filterRuleMap.put("/store/getAll","jwt");
//        filterRuleMap.put("/**","jwt");
//        filterRuleMap.put("/**/**","jwt");
//        filterRuleMap.put("/**/**/**","jwt");
//        filterRuleMap.put("/account/register", "anon");
//        filterRuleMap.put("/account/login", "anon");
//        filterRuleMap.put("/account/test", "anon");
//        filterRuleMap.put("/judge", "jwt");
//        filterRuleMap.put("/discuss","jwt");
//        filterRuleMap.put("/discuss/**","jwt");
//        filterRuleMap.put(Constants.REFRESH_TOKEN_REDIRECT_METHOD,"anon");
//        filterRuleMap.put(Constants.ACCESS_DENIED_REDIRECT_METHOD,"anon");

        filterRuleMap.put("/account/*", "anon");
        filterRuleMap.put("/article/test", "anon");
        filterRuleMap.put("/article/get-publish-article-list-page", "anon");
        filterRuleMap.put("/article/get-all-article-list-page", "jwt");
        filterRuleMap.put("/article/get-all-article-list-all", "jwt");
        filterRuleMap.put("/article/new-article", "jwt");
        filterRuleMap.put("/article/save-article", "jwt");
        filterRuleMap.put("/article/publish-article", "jwt");
        filterRuleMap.put("/article/get-article-by-id", "anon");
        filterRuleMap.put("/anthology/get-all-anthology", "anon");
        filterRuleMap.put("/picture/upload-picture", "jwt");
        filterRuleMap.put("/picture/get-picture", "anon");

        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        factoryBean.setUnauthorizedUrl("/account/401");

        return factoryBean;
    }

    /*不加这个shiro注解就不生效*/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /*启用@RequiresRoles等注解*/
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

}
