package com.example.demosecurity.repository;

import com.example.demosecurity.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Account, Long> {


}
