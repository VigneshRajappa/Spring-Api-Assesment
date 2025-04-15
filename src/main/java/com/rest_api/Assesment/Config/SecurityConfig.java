package com.rest_api.Assesment.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rest_api.Assesment.Service.MyUserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf->csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
					.requestMatchers("/api/patinet/getByDoctorId/{docId}").hasAuthority("DOCTOR")
					.requestMatchers("/api/doctor/patient/add/{docId}/{patId}").permitAll()
					.requestMatchers("/api/patient/add").hasAuthority("PATIENT")
					.anyRequest().permitAll()
					)
					.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
					;

				return http.build();
			}
			@Bean
			AuthenticationProvider getAuth() {
				DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
				dao.setPasswordEncoder(passEncoder());
				dao.setUserDetailsService(myUserService);	
				return dao;
			}
			@Bean
			BCryptPasswordEncoder passEncoder() {
				return new BCryptPasswordEncoder();
			}
			
			@Bean
			AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
				  return auth.getAuthenticationManager();
			 }
		}


