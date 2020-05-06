package com.hendisantika.springsecurityssoauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-security-sso-auth-server
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/05/20
 * Time: 05.14
 */
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and().csrf().disable();
    } // @formatter:on

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
//        auth.inMemoryAuthentication()
//            .withUser("henry")
//            .password(passwordEncoder().encode("123"))
//            .roles("USER");

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled"
                        + " from users where username=?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from authorities where username=?")
                .passwordEncoder(passwordEncoder());
    } // @formatter:on

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
