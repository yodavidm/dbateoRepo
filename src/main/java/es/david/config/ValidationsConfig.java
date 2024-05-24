package es.david.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.david.services.UserValidation;

@Configuration
public class ValidationsConfig {
	
	@Bean
	public UserValidation userValidation() {
		return new UserValidation();
	}

}
