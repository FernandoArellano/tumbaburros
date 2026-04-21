package com.example._3_ms.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthManager implements ReactiveAuthenticationManager {

    @Value("${clave}")
    private String CLAVE;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        //primeramente, transforma el Mono<Authentication> en un Mono<Claims>, incluyendo la información
        //recibida en el token JWT. Después, ese Mono<Authentication> es transformado en un nuevo
        //Mono<Authentication> generado a partir de un UserPasswordAuthenticationToken que se configura
        //a partir del usuario y roles del token
        return Mono.just(authentication)
                .map(auth-> Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
                        .build()
                        .parseSignedClaims(auth.getCredentials().toString().replace("Bearer ", ""))
                        .getPayload())//Mono<Claims>
                .switchIfEmpty(Mono.empty())
                .map(claims->new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        ((List<String>)claims.get("authorities", List.class)).stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList()
                                )));//Mono<Authentication>
    }
}
