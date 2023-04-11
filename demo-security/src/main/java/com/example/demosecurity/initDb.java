package com.example.demosecurity;

import com.example.demosecurity.domain.Account;
import com.example.demosecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        this.initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final UserService userService;
        private final PasswordEncoder encoder;
        public void dbInit() {
            Account user = new Account();
            user.setUsername("user");
            user.setPassword(encoder.encode("1111"));
            user.setRole("ROLE_USER");
            userService.createUser(user);

            Account manager = new Account();
            manager.setUsername("manager");
            manager.setPassword(encoder.encode("1111"));
            manager.setRole("ROLE_MANAGER");
            userService.createUser(manager);
        }
    }
}
