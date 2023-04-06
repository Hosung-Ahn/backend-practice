package com.example.demosecurity.service;

import com.example.demosecurity.domain.Account;
import com.example.demosecurity.repository.UserRepository;

public interface UserService {

    void createUser(Account account);
}
