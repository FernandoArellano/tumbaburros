package com.eazybites.gateway;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.SecretKey;

@SpringBootApplication
@EnableWebFluxSecurity
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	private final String SECRET = "my-super-secret-key-that-is-at-least-32-bytes!!";

	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http){

		return http
				.csrf( csrfSpec -> csrfSpec.disable())
				.authorizeExchange( authorizeExchangeSpec -> {

					authorizeExchangeSpec
							.pathMatchers("/auth/**").permitAll()
                            .pathMatchers("/api/inventory/tests/**").permitAll()
							.pathMatchers("/eureka/**").permitAll()
							.anyExchange().authenticated();
				})
				.oauth2ResourceServer(oauth -> oauth
						.jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))
				)
				.build();
	}

	@Bean
	public ReactiveJwtDecoder jwtDecoder() {
		SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
		return NimbusReactiveJwtDecoder.withSecretKey(key).build();
	}


}
