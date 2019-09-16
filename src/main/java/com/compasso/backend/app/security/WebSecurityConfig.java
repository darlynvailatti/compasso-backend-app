package com.compasso.backend.app.security;

import com.compasso.backend.app.domain.entity.UserEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.service.IUserService;
import com.compasso.backend.app.util.ExpectThat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@Value("${app.default.admin.password}")
	private String defaultAdminPasswod;
	
	@Value("${app.default.admin.username}")
	private String defaultAdminUserName;
	
	@Value("${app.default.authentication.path}")
	private String authenticationPath;
	
	@Autowired
	private IUserService userService;
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		createAdminUser();
		log.info("Configuring httpSecurity...: {}", contextPath);
		
		JWTLoginFilter jwtLoginFilter = new JWTLoginFilter(authenticationPath, authenticationManager());
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET,"/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
			.antMatchers(HttpMethod.POST, authenticationPath).permitAll()
			.anyRequest().authenticated()
			.and()

			// filtra requisições de login
			.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)

			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		createAdminUser();
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	 
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
	}
	
	private void createAdminUser() throws BusinessLogicException {
		try {
			log.info("Creating default admin user {}...", defaultAdminPasswod);
			UserDetails adminUser = loadDefaultAdminUser();
			if(ExpectThat.isNull(adminUser)) {
				UserEntity admin = new UserEntity();
				String encodePassword = encoder().encode(defaultAdminPasswod);
				admin.setPassword(encodePassword);
				admin.setUsername(defaultAdminUserName);
				admin.setNonExpired(true);
				admin.setNonLocked(true);
				admin.setCredentialsNonExpired(true);
				admin.setEnabled(true);
				userService.confirm(admin);
			}
		} catch (Exception e) {
			Exception errorCreateAdminUser = new Exception("Error on create admin user. Cause: " + e.getMessage());
			throw new BusinessLogicException(errorCreateAdminUser);
		}
	}

	private UserDetails loadDefaultAdminUser() {
		try {
			return userService.loadUserByUsername(defaultAdminUserName);
		} catch (UsernameNotFoundException e) {
			return null;
		}
	}

	
}
