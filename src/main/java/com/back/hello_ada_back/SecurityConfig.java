package com.back.hello_ada_back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.back.hello_ada_back.services.CustomUserDetailsService;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder
				.userDetailsService(customUserDetailsService)
				.passwordEncoder(passwordEncoder()); // Utilisation du PasswordEncoder
		return authenticationManagerBuilder.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable())
			.headers(headers -> headers.frameOptions().sameOrigin())
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers("/h2-console/**").permitAll();
				auth.requestMatchers("/api/users/createUser").permitAll();
				auth.requestMatchers("/api/users").permitAll();
				auth.requestMatchers("/api/users/*").permitAll();
				auth.requestMatchers("/api/users/*/updateProfile").permitAll();
				auth.requestMatchers("/api/posts").permitAll();
				auth.requestMatchers("/api/posts/*").permitAll();
				auth.requestMatchers("/api/posts/user/*").permitAll();
				auth.requestMatchers("/api/posts/createPost").permitAll();
				auth.requestMatchers("/login").permitAll();
				auth.anyRequest().authenticated();
			})
			.formLogin(form -> form
                .defaultSuccessUrl("/api/users", true)
                .permitAll()
            )
			.httpBasic(basic -> {});         
		
		return http.build();
	}

	@Bean
	UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
		configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Remplace NoOpPasswordEncoder
	}
}
