package es.david.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.david.services.IJWTUtilityService;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private IJWTUtilityService jwtUtilityService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(authRequest->authRequest
						.requestMatchers("/publicaciones/crear").authenticated()
						.requestMatchers("/auth/**","/validar","/publicaciones","/user/{id}","/publicaciones/{id}","/publicaciones/categoria/{id}","/comentarios","/comentarios/publicacion/{id}").permitAll()  //Aqui ire todas las rutas publicas
							.anyRequest().authenticated() //aqui se implementa roles ADMIN Y USER EN UN FUTURO
				)
				.sessionManagement(sessionManager->
					sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
				.addFilterBefore(new JWTAuthorizationFilter(jwtUtilityService),
						UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(exceptionHandling->exceptionHandling
						.authenticationEntryPoint((request,response,authException)->{
							response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
						}))
				.build();
						
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
