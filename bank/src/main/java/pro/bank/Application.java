package pro.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;

import pro.bank.viewresolver.JsonViewResolver;


@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer  {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	} 
}
