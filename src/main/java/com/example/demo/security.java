package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class security extends WebSecurityConfigurerAdapter {
    private final userDetailService accountService;

    @Bean
    public BCryptPasswordEncoder pwdEncoder() {
       return  new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/","/auth/**","/login","/static/**").permitAll().anyRequest().authenticated()
        .and().formLogin().loginPage("/index.html").loginProcessingUrl("/loginpro").defaultSuccessUrl("/")
        .and().rememberMe().userDetailsService(accountService).rememberMeParameter("remember-me").key("key");
    }
   
}
