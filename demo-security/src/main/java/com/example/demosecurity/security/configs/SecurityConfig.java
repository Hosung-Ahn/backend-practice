package com.example.demosecurity.security.configs;

import com.example.demosecurity.security.common.FormAuthenticationDetailsSource;
import com.example.demosecurity.security.filter.AjaxLoginProcessingFilter;
import com.example.demosecurity.security.handler.CustomAccessDeniedHandler;
import com.example.demosecurity.security.handler.CustomAuthenticationFailureHandler;
import com.example.demosecurity.security.handler.CustomAuthenticationSuccessHandler;
import com.example.demosecurity.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configurable
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    // 메모리에 유저를 생성한다.
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("1111"))
//                .roles("USER")
//                .build();
//        UserDetails manager = User.builder()
//                .username("manager")
//                .password(passwordEncoder().encode("1111"))
//                .roles("MANAGER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("1111"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, manager, admin);
//    }

    private final UserDetailsService userDetailsService;

    private final FormAuthenticationDetailsSource formAuthenticationDetailsSource;

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(new CustomAuthenticationProvider(userDetailsService, passwordEncoder()));
        authenticationManagerBuilder.parentAuthenticationManager(null);

        http
                .authorizeRequests()
                .antMatchers("/", "/register", "/login", "/login?error*").permitAll()
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .authenticationDetailsSource(formAuthenticationDetailsSource)
                .defaultSuccessUrl("/")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())

                .and()
                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();

//        return http.userDetailsService(userDetailsService).build();
        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 파일에 대한 접근에 대해서는 보안필터를 적용하지 않게 합니다.
        return (web) -> {
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return ajaxLoginProcessingFilter;
    }
}
