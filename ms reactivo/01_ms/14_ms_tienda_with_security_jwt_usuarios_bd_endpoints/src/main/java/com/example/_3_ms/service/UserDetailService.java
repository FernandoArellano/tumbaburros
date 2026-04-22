package com.example._3_ms.service;

import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;


public interface UserDetailService {
    Mono<UserDetails> findByUsername(String user);
}
