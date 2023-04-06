package com.example.demosecurity.controller.user;

import com.example.demosecurity.domain.Account;
import com.example.demosecurity.domain.AccountDto;
import com.example.demosecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @GetMapping("mypage")
    public String myPage() throws Exception {
        return "user/mypage";
    }

    @GetMapping("/register")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping("/register")
    public String createUser(AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(encoder.encode(accountDto.getPassword()));
        account.setAge(accountDto.getAge());
        account.setEmail(accountDto.getEmail());
        account.setRole(accountDto.getRole());

        userService.createUser(account);

        return "redirect:/";
    }
}
