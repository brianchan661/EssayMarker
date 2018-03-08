package com.prolancer.essaymarker.config;

import com.prolancer.essaymarker.service.UserSecurityService;
import com.prolancer.essaymarker.type.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserSecurityService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/signup", "/h2").permitAll()
                .antMatchers("/assets/**").permitAll()
                .anyRequest().hasAuthority(Role.USER.toString())
            .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/index")
            .and()
                .logout().permitAll().logoutSuccessUrl("/login")
                .and().headers().frameOptions().sameOrigin()
            .and()
                .csrf().disable();
    }
}
