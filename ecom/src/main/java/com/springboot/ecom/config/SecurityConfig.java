package com.springboot.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService users() {
        UserDetails customer1 = User.builder()
                .username("customer1")
                .password("{noop}customer1")
                .roles("CUSTOMER")
                .build();
        UserDetails customer2 = User.builder()
                .username("customer2")
                .password("{noop}customer2")
                .roles("CUSTOMER")
                .build();
        UserDetails executive1 = User.builder()
                .username("executive1")
                .password("{noop}executive1")
                .roles("EXECUTIVE")
                .build();
        UserDetails seller1 = User.builder()
                .username("seller1")
                .password("{noop}seller1")
                .roles("SELLER")
                .build();

        return new InMemoryUserDetailsManager(customer1, customer2, executive1, seller1);
    }
    @Bean
    public SecurityFilterChain securedFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/product/by-category/{categoryId}").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
