package com.example._3_ms.service;

import com.example._3_ms.model.Usuario;
import com.example._3_ms.repository.RolRepository;
import com.example._3_ms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService{

    private final RolRepository rolRepository;
    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        return userRepository.findByUser(username) //Mono<Usuario>
                .flatMap((Usuario us) -> rolRepository.findByIdUser(username)   //Flux<Rol>
                        .map(r->r.getId().getRol()) //Flux<String>
                        .collectList()//Mono<List<String>>
                        .map(roles -> User.withUsername(us.getUser())
                                .password(us.getPwd())
                                .roles(roles.toArray(new String[0]))
                                .build())) //Mono<UserDetails>
                .switchIfEmpty(Mono.empty());
    }
}
