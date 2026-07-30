package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.AdminDto;
import com.springboot.ecom.dto.response.TokenDto;
import com.springboot.ecom.dto.response.UserRespDto;
import com.springboot.ecom.model.User;
import com.springboot.ecom.service.UserService;
import com.springboot.ecom.utility.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import static org.slf4j.LoggerFactory.*;
import java.security.Principal;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {

    private final UserService userService;
    private final JwtUtility jwtUtility;
    private Logger logger =  LoggerFactory.getLogger("AuthController.class");

    @PostMapping("/add/admin")
    public void addAdmin(@RequestBody AdminDto adminDto){
        userService.addAdmin(adminDto);
    }

    // Before the user hits this /login api here in controller, spring would have already checked credentials
    @GetMapping("/login")
    public TokenDto login(Principal principal){
        String loggedInUsername = principal.getName();
        logger.info("Logged In Username {}", loggedInUsername );
        System.out.println("Logged In Username {}"+ loggedInUsername);
        // Generate the token for this username
        String token = jwtUtility.generateToken(loggedInUsername);
        logger.info("Token Generated {}", token );
        // fetch user details to pass the role
        User user =  userService.getUserDetails(loggedInUsername);
        logger.info("User Details fetched from DB having role: {}", user.getRole() );
        logger.info("Token Expiry {}", jwtUtility.extractExpiration(token).toString());
        return new TokenDto(
                token,
                jwtUtility.extractExpiration(token).toString(),
                user.getRole().toString()
        );
    }

    @GetMapping("/user-details")
    public UserRespDto getUserDetails(Principal principal){
        logger.info("Fetching details of User {}", principal.getName());
        String loggedInUsername = principal.getName();
        User user =  userService.getUserDetails(loggedInUsername);
         return new UserRespDto(
                 loggedInUsername,
                 user.getRole().toString()
         );
    }
}
