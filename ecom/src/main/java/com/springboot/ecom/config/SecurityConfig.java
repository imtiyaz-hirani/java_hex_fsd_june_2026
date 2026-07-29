package com.springboot.ecom.config;

import com.springboot.ecom.service.MyUserSecurityService;
import com.springboot.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserSecurityService myUserSecurityService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securedFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers("/api/category/all").permitAll()
                        .requestMatchers("/api/product/by-category/{categoryId}").permitAll()

                        // Auth APIs
                        .requestMatchers("/api/auth/login").authenticated()

                        // Sign Up Insert user APIs
                        .requestMatchers(HttpMethod.POST, "/api/auth/add/admin").denyAll()

                        .requestMatchers(HttpMethod.POST,"/api/executive/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/customer/add").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/seller/add").hasAnyAuthority("ADMIN", "EXECUTIVE")

                        // Seller API
                        .requestMatchers("/api/seller/de-activate").hasAnyAuthority("SELLER", "ADMIN")

                        // Executive API
                        .requestMatchers("/api/product/count/for-each-seller").hasAuthority("EXECUTIVE")
                        .requestMatchers("/api/product/purchase/by-customer").hasAnyAuthority("EXECUTIVE", "CUSTOMER")

                        /* Travel Case Study APIs */
                        .requestMatchers(HttpMethod.GET, "/api/passenger/all").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/passenger/add").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
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
