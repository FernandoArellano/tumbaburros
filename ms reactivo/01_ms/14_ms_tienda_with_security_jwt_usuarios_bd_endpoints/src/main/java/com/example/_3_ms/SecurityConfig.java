package com.example._3_ms;

import com.example._3_ms.config.AuthManager;
import com.example._3_ms.config.SecurityContextRepository;
import com.example._3_ms.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@EnableWebFluxSecurity
@Configuration
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final UserDetailService detailService;

    @Bean
    public ReactiveUserDetailsService userDetailsService() throws Exception{
        return user->detailService.findByUsername(user);
    }

    @Bean
    public SecurityWebFilterChain filter(ServerHttpSecurity http){
            http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .authenticationManager(authenticationManager)
                    .securityContextRepository(securityContextRepository)
                    .authorizeExchange(
                            auth->auth.pathMatchers(HttpMethod.POST,"/productos").hasAnyRole("ADMIN")
                                    .pathMatchers(HttpMethod.DELETE,"/productos/**").hasAnyRole("ADMIN","OPERATOR")
                                    .pathMatchers("/productos/**").authenticated()
                                    .anyExchange().permitAll());
            return http.build();

    }
}
