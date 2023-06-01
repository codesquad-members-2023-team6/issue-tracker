package com.team6.issue_tracker.global.config;

import com.team6.issue_tracker.global.auth.service.JwtService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login/**","/oauth/**",
                        "/issue/**",
                        "/error", "/swagger-ui/**",
                        "/v3/**", "auth/**",
                        "/issue?status=open&page=0&maxPageNum=10").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .logoutSuccessUrl("http://www.issuetrackerjakso.site/login")
                .and()
                .apply(new JwtSecurityConfig(jwtService));
    }
}
