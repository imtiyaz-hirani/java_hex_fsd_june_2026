package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.AdminDto;
import com.springboot.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/add/admin")
    public void addAdmin(@RequestBody AdminDto adminDto){
        userService.addAdmin(adminDto);
    }
}
