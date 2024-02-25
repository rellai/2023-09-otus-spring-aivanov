package ru.otus.aivanov.home16.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    @Order(1)
    public SecurityFilterChain nullSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/monitor", "/monitor/**")
                .authorizeHttpRequests((request) -> request

                        .anyRequest()
                        .permitAll()

                )
        ;

        return http.build();
    }    


    @Bean
    @Order(2)
    public SecurityFilterChain firstSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/**")
                .authorizeHttpRequests((request) -> request

                        .requestMatchers(HttpMethod.POST, "/api/comments")
                        .hasRole("USER")

                        .requestMatchers(HttpMethod.PUT, "/api/comments/*")
                        .hasRole("USER")

                        .requestMatchers(HttpMethod.DELETE, "/api/comments/*")
                        .hasRole("ADMIN")


                        .requestMatchers(HttpMethod.DELETE, "/api/books/*")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .hasAnyRole("USER","ADMIN")
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain secondSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request

                        .requestMatchers("/monitor/")
                        .permitAll()


                        .anyRequest()
                        .hasAnyRole("USER","ADMIN")
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .rememberMe(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}