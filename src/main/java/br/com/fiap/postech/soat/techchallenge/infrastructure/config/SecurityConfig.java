package br.com.fiap.postech.soat.techchallenge.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/techchallenge/v1/swagger-ui/**",
//                                "/techchallenge/v1/api-docs/**",
//                                "/techchallenge/v1/v3/api-docs/**",
//                                "/techchallenge/v1/swagger-resources/**",
//                                "/techchallenge/v1/webjars/**",
//                                "/techchallenge/v1/products/**" // libera seu controller
//                        ).permitAll()
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()

                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
