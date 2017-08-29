package com.fb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan("com.fb")
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProvider authenticationProvider;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(new SimpleCORSFilter(), CorsFilter.class)
                .authorizeRequests()

                .antMatchers("/registration",
                                        "/logout-success",
                                        "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("login")
                .passwordParameter("password")
                .loginPage("/login")
                .successForwardUrl("/api/hello")
                .failureForwardUrl("/logout-success")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/logout-success")
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

}