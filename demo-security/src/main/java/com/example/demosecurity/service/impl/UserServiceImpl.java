package com.example.demosecurity.service.impl;

import com.example.demosecurity.domain.Account;
import com.example.demosecurity.repository.UserRepository;
import com.example.demosecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
