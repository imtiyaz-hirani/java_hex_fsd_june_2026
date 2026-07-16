package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.AdminDto;
import com.springboot.ecom.enums.Role;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addAdmin(AdminDto adminDto) {
        // Prepare the user
        User user = new User();
        user.setUsername(adminDto.username());
        user.setPassword(passwordEncoder.encode(adminDto.password()));
        user.setRole(Role.ADMIN);
        user.setActivated(true);

        // save user in DB
        userRepository.save(user);
    }


}
