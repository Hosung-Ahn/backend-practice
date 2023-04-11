package com.example.demosecurity.security.configs;

import com.example.demosecurity.security.common.AjaxLoginAuthenticationEntryPoint;
import com.example.demosecurity.security.filter.AjaxLoginProcessingFilter;
import com.example.demosecurity.security.handler.AjaxAccessDeniedHandler;
import com.example.demosecurity.security.handler.AjaxAuthenticationFailureHandler;
import com.example.demosecurity.security.handler.AjaxAuthenticationSuccessHandler;
import com.example.demosecurity.security.provider.AjaxAuthenticationProvider;
import com.example.demosecurity.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Order(0)
public class AjaxSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    private final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    private final AjaxLoginAuthenticationEntryPoint ajaxLoginAuthenticationEntryPoint;
    private final AjaxAccessDeniedHandler ajaxAccessDeniedHandler;



    @Bean
    public SecurityFilterChain ajaxFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(ajaxAuthenticationProvider());

        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/messages").hasRole("MANAGER")
                .anyRequest().authenticated()

                .and()
                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

        http
                .exceptionHandling()
                .authenticationEntryPoint(ajaxLoginAuthenticationEntryPoint)
                .accessDeniedHandler(ajaxAccessDeniedHandler)
        ;


        http.csrf().disable();

        return http.build();
    }

    @Bean
    public AuthenticationProvider ajaxAuthenticationProvider() {
        return new AjaxAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler);
        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler);
        return ajaxLoginProcessingFilter;
    }

}
