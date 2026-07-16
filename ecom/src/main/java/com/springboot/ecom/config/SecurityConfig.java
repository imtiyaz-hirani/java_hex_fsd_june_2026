package com.springboot.ecom.config;

import com.springboot.ecom.service.MyUserSecurityService;
import com.springboot.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserSecurityService myUserSecurityService;

    @Bean
    public SecurityFilterChain securedFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/product/by-category/{categoryId}").permitAll()
                        .requestMatchers("/api/product/count/for-each-seller").hasAuthority("EXECUTIVE")
                        .requestMatchers("/api/product/purchase/by-customer").hasAnyAuthority("EXECUTIVE", "CUSTOMER")

                        // Sign Up Insert user APIs
                        .requestMatchers(HttpMethod.POST, "/api/auth/add/admin").denyAll()

                        .requestMatchers(HttpMethod.POST,"/api/executive/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/customer/add").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/seller/add").hasAnyAuthority("ADMIN", "EXECUTIVE")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(myUserSecurityService);
        dao.setPasswordEncoder(getEncoder());
        return dao;
    }
}
