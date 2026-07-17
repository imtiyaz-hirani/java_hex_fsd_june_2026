package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.AdminDto;
import com.springboot.ecom.dto.response.TokenDto;
import com.springboot.ecom.model.User;
import com.springboot.ecom.service.UserService;
import com.springboot.ecom.utility.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtility jwtUtility;

    @PostMapping("/add/admin")
    public void addAdmin(@RequestBody AdminDto adminDto){
        userService.addAdmin(adminDto);
    }

    // Before the user hits this /login api here in controller, spring would have already checked credentials
    @GetMapping("/login")
    public TokenDto login(Principal principal){
        String loggedInUsername = principal.getName();
        // Generate the token for this username
        String token = jwtUtility.generateToken(loggedInUsername);

        // fetch user details to pass the role
        User user =  userService.getUserDetails(loggedInUsername);

        return new TokenDto(
                token,
                jwtUtility.extractExpiration(token).toString(),
                user.getRole().toString()
        );
    }
}
