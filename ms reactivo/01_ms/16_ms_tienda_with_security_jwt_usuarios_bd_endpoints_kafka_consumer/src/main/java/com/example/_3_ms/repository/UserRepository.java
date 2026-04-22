package com.example._3_ms.repository;

import com.example._3_ms.model.Usuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Usuario, String> {

    Mono<Usuario> findByUser(String user);
}
