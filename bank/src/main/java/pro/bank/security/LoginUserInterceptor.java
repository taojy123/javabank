package pro.bank.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pro.bank.entity.Admin;

@Component
public class LoginUserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
		Object principal = SecurityUtils.getSubject().getPrincipal();
		if(principal!=null && principal instanceof Admin){
			Admin user = (Admin) principal;
			if (user != null) {
				httpServletRequest.setAttribute("loginUser", user);
			}
		}else{
			//httpServletResponse.sendRedirect("/student/info");
		}
	}

}

@Configuration
class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor());
	}

	@Bean
	HandlerInterceptor interceptor() {
		return new LoginUserInterceptor();
	}
}
