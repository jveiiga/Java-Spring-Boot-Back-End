package com.example.cursomc.config;

import java.util.Arrays;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.cursomc.security.JWTAuthenticationFilter;
import com.example.cursomc.security.JWTAuthorizationFilter;
import com.example.cursomc.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
// @EnableGlobalMethodSecurity(prePostEnabled = true) --> está depreciada na nova versão do Spring Boot 3.01.
// public class SecurityConfig extends WebSecurityConfigurerAdapter { --> foi depreciada na versão 5.7.0-M2 do Spring Security12.
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	// @Autowired
    // private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};

	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**",
			"/estados/**"
	};

	private static final String[] PUBLIC_MATCHERS_POST = {
			"/clientes/**",
			"/auth/forgot/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Não funciona no Java 17
		// if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
        //     http.headers().frameOptions().disable();
        // }
		

		// http.cors().and().csrf().disable(); --> foi depreciado
		http.csrf(csrf -> csrf.disable())
   			.cors(cors -> cors.disable());
		// http.authorizeRequests() --> foi depreciado
			// .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			// .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			// .antMatchers(PUBLIC_MATCHERS).permitAll()
			// .anyRequest().authenticated();
		http.authorizeHttpRequests(authz -> authz
   			.requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
   			.requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
   			.requestMatchers(PUBLIC_MATCHERS).permitAll()
   			.anyRequest().authenticated());

		// http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil)); --> a maneira de adicionar filtros mudou. Agora, é preciso usar a abordagem do Customizer.
		http.addFilterAt(new JWTAuthenticationFilter(authenticationManager(), jwtUtil), SecurityFilterChain.class);
		// http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService)); --> a maneira de adicionar filtros mudou. Agora, é preciso usar a abordagem do Customizer.
		http.addFilterAt(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService), SecurityFilterChain.class);
		// http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); --> a maneira de adicionar filtros mudou. Agora, é preciso usar a abordagem do Customizer.
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

