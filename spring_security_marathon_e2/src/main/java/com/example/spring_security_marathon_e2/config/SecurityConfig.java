package com.example.spring_security_marathon_e2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        System.out.println("IN SECURITY FILTER CHAIN");


        /*
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/user")) // Disables CSRF protection, common in stateless REST APIs.
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/user", "POST")).permitAll() // Allow POST requests to /user without authentication
                        .requestMatchers("/demo").hasAuthority("read")
                        .requestMatchers("/test").hasAuthority("write")
                        .requestMatchers("/smth").access(String.valueOf(new WebExpressionAuthorizationManager("isAuthenticated()")))
                        .anyRequest().authenticated() // Ensures all requests are authenticated.
                )
                .httpBasic(withDefaults()); // Enables HTTP Basic Authentication with default settings.

        return http.build();
        */

        http.httpBasic();

        http.authorizeHttpRequests()
                .requestMatchers("/user").permitAll()
                .requestMatchers("/demo").hasAuthority("read")
                .requestMatchers("/write").hasAuthority("write")
                .requestMatchers("/smth").access(new WebExpressionAuthorizationManager("isAuthenticated()"))
                .anyRequest().authenticated();

        http.csrf().ignoringRequestMatchers("/user");

        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
