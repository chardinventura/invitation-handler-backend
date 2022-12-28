package com.marcomc.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST, "/api/people/invitation")
		.permitAll()
		.requestMatchers("/api/invitations/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.defaultSuccessUrl("/invitations", true)
		.permitAll()
		.and()
		.build();
	}
}
