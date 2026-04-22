package com.example._3_ms.repository;

import com.example._3_ms.model.Rol;
import com.example._3_ms.model.RolPk;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RolRepository extends ReactiveCrudRepository<Rol, RolPk> {

    @Query(value = "select * from roles where roles.user=?")
    Flux<Rol> findByIdUser(String user);
}
