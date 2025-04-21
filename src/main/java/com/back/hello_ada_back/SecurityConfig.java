package com.back.hello_ada_back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




import com.back.hello_ada_back.services.CustomUserDetailsService;



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
        .passwordEncoder(passwordEncoder());  // Utilisation du PasswordEncoder
    return authenticationManagerBuilder.build();
}

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers
				.frameOptions()
				.sameOrigin()
			)
			.authorizeHttpRequests(auth -> {
                auth.requestMatchers("/h2-console/**").permitAll(); 
                auth.requestMatchers("/api/users/createUser").permitAll(); 
				auth.requestMatchers("/login").permitAll();
				auth.anyRequest().authenticated();
			})
			.formLogin(form -> form
				.defaultSuccessUrl("/api/posts", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/api/logout")  
				.logoutSuccessUrl("/login")  
				.invalidateHttpSession(true)  
				.deleteCookies("JSESSIONID")  
				.permitAll()
			);

		return http.build();
	}

    // @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails user =
	// 		 User.withDefaultPasswordEncoder()
	// 			.username("user")
	// 			.password("password")
	// 			.roles("USER")
	// 			.build();

	// 	return new InMemoryUserDetailsManager(user);
	// }

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();  // Remplace NoOpPasswordEncoder
	}
}
