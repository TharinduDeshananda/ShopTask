package com.grouptd.shop.securityconfigs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grouptd.shop.models.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService customUserDetailsService;

    @Autowired
    JwtSecurityFilter securityFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().anyRequest().permitAll();


        http.csrf().disable()
        .authorizeRequests()
                .antMatchers("/authenticate","/createAdmin","/getPrinciple","/refreshtoken").permitAll()
                .antMatchers("/createCashier").hasRole("ADMIN")
                .antMatchers("/admins/**").hasRole("ADMIN")
                .antMatchers("/customers/**").hasAnyRole("ADMIN","CASHIER")
                .antMatchers("/cashiers/**").hasAnyRole("ADMIN","CASHIER")
                .antMatchers("/customerorders/**").hasAnyRole("ADMIN","CASHIER")
//                .anyRequest().authenticated().and().
                .and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
                System.out.println("Sending authenticated users access denied");
                    ObjectMapper mapper = new ObjectMapper();
                    ErrorResponse errResponse = new ErrorResponse("You have no access rights");
                    mapper.writeValue(response.getWriter(),errResponse);
                }).authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getWriter(),new ErrorResponse("You have no access rights"));
            }
        });

        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
