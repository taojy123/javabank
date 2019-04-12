package pro.bank.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import pro.bank.utils.StringUtil;

public final class SpringTool {  
    private static  ApplicationContext applicationContext = null;  
  
    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
		SpringTool.applicationContext = applicationContext;  
    }  
  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
	public  static Object getBean(Class<?> cla){
        return applicationContext.getBean(StringUtil.toLowerCaseFirstOne(cla.getSimpleName()));
    }
          
}  
