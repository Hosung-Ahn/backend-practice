package com.example.demosecurity.security.provider;

import com.example.demosecurity.security.common.FormWebAuthenticationDetails;
import com.example.demosecurity.security.service.AccountContext;
import com.example.demosecurity.security.token.AjaxAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        AccountContext accountContext = (AccountContext)userDetailsService.loadUserByUsername(username);

        if (!encoder.matches(password, accountContext.getAccount().getPassword())) {
            log.error("비밀번호가 일치하지 않습니다.");
            throw new BadCredentialsException(URLEncoder.encode("비밀번호가 일치하지 않습니다.", StandardCharsets.UTF_8));
        }


        AjaxAuthenticationToken authenticationToken =
                        new AjaxAuthenticationToken(accountContext.getAccount(),
                        null, accountContext.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AjaxAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
