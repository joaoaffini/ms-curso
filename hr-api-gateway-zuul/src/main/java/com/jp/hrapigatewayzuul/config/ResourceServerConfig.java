package com.jp.hrapigatewayzuul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private static final String[] PUBLIC = {"/hr-oauth/oauth/token"};
	private static final String[] OPERATOR = {"/hr-worker/**"};
	private static final String[] ADMIN = {"/hr-payroll/**", "/hr-user/**", "/actuator/**", 
			"/hr-worker/actuator/**",  "/hr-oauth/actuator/**"};
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN")
		.antMatchers(ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated();
		
		http.cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	
		CorsConfiguration corsConfig = new CorsConfiguration();
		//Qual origem sera liberada, neste caso todas
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		//Quais metodos http serao liberados
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "PUT", "PATCH"));
		//vai permitir credenciais = true
		corsConfig.setAllowCredentials(true);
		//cabecalhos permitidos
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// /** = significa que todos os caminhos terao esta configuracao de cors
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return bean;
	}
	
	

}
