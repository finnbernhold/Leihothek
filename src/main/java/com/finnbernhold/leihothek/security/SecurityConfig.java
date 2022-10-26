package com.finnbernhold.leihothek.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)

public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated();
        return http.build();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource){return new JdbcUserDetailsManager(dataSource);}
}
