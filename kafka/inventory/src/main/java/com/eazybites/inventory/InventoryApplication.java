package com.eazybites.inventory;

import com.eazybites.inventory.dto.ConfigurationPropertiesTest;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;


import javax.crypto.SecretKey;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableWebSecurity
@EnableConfigurationProperties(ConfigurationPropertiesTest.class)
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	private final String SECRET = "my-super-secret-key-that-is-at-least-32-bytes!!";

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http){

			return http.csrf(csrfSpec -> csrfSpec.disable())
				.authorizeHttpRequests(auth ->
						auth.requestMatchers("/api/inventory/tests/**").permitAll()
						.anyRequest().authenticated())
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
