package pro.bank.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import pro.bank.security.SampleRealm;

@Configuration
public class SecurityConfig {

	@Bean
	public SampleRealm sampleRealm() {
		return new SampleRealm();
	}

	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public WebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(sampleRealm());
		return securityManager;
	}

	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		methodInvokingFactoryBean.setArguments(new Object[] { securityManager() });
		return methodInvokingFactoryBean;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setLoginUrl("/count");
		shiroFilter.setSuccessUrl("/count");
		shiroFilter.setUnauthorizedUrl("/count");

		HashMap<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("authc", new PassThruAuthenticationFilter());
		// filters.put("authc", new FormAuthenticationCaptchaFilter());
		shiroFilter.setFilters(filters);
		Map<String, String> definitions = shiroFilter.getFilterChainDefinitionMap();
		definitions.put("/templates/**", "anon");
		definitions.put("/captcha_login", "anon");
		definitions.put("/static/**", "anon");
		definitions.put("/static/images/**", "anon");
		definitions.put("/images/**", "anon");
		definitions.put("/signup", "anon");
		definitions.put("/css/**", "anon");
		definitions.put("/js/**", "anon");
		definitions.put("/fonts/**", "anon");
		definitions.put("/i/**", "anon");
		definitions.put("/img/**", "anon");
		definitions.put("/api/**", "anon");
		definitions.put("/**", "anon");
		definitions.put("/", "anon");
		shiroFilter.setFilterChainDefinitionMap(definitions);

		return shiroFilter;
	}

}