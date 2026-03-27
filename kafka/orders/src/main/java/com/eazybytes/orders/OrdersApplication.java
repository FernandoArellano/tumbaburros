package com.eazybytes.orders;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

@SpringBootApplication
@EnableFeignClients
@EnableAspectJAutoProxy
@EnableWebSecurity
public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	private final String SECRET = "my-super-secret-key-that-is-at-least-32-bytes!!";

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http){

		return http.csrf(csrfSpec -> csrfSpec.disable())
				.authorizeHttpRequests(auth ->
						auth.anyRequest().authenticated())
				.oauth2ResourceServer(oauth -> oauth
						.jwt(jwt -> jwt.decoder(jwtDecoder()))
				)
				.build();

	}

	@Bean
	public JwtDecoder jwtDecoder() {
		SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
		return NimbusJwtDecoder.withSecretKey(key).build();
	}
}
