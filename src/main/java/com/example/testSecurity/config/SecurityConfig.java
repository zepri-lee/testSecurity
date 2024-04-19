package com.example.testSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests((auth) -> auth
					.requestMatchers("/", "/login", "/loginProc").permitAll()               // 모든 사용자 접근 가능
					.requestMatchers("/admin").hasRole("ADMIN")               // ADMIN Role만 접근 가능
					.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")    // ADMIN, USER Role만 접근 가능
					.anyRequest().authenticated()                             // 인증 사용자만 접근 가능
					);
		
        http
        	.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .permitAll()
        );

        http
        	.csrf((auth) -> auth.disable());		
		
		
		return http.build();
	}
}
