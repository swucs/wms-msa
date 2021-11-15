package com.sycoldstorage.adminservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    /**
     * 인증
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider());
    }

    /**
     * 권한
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/actuator/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter();
        loginAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        loginAuthenticationFilter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler());
        loginAuthenticationFilter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler());
        return loginAuthenticationFilter;
    }

    @Bean
    public LoginAuthenticationProvider loginAuthenticationProvider() {
        return new LoginAuthenticationProvider();
    }

    @Bean
    public LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler() {
        return new LoginAuthenticationSuccessHandler();
    }

    @Bean
    public LoginAuthenticationFailureHandler loginAuthenticationFailureHandler() {
        return new LoginAuthenticationFailureHandler();
    }

}
