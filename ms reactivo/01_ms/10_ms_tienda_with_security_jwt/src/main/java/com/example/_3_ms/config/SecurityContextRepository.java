package com.example._3_ms.config;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private final AuthManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, @Nullable SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith("Bearer ")) //Mono<String>
                .flatMap(authHeader ->
                        this.authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(authHeader.substring(7), authHeader.substring(7))
                                )
                                .map(SecurityContextImpl::new)
                );
    }
}
