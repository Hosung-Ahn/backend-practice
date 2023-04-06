package com.example.demosecurity.security.service;

import com.example.demosecurity.domain.Account;
import com.example.demosecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        // 권환을 한개로 가정햇지만, 본래 권한은 복수일 수 있습니다.
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole()));

        AccountContext accountContext = new AccountContext(account, authorities);

        return accountContext;
    }
}
