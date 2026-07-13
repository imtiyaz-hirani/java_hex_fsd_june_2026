package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.ExecutiveReqDto;
import com.springboot.ecom.service.ExecutiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/executive")
@RequiredArgsConstructor
public class ExecutiveController {

    private final ExecutiveService executiveService;
    /*
    Executive with User
    Body:
    {
        name : "",
        jobTitle : "",
        username : "",
        password: ""
    }
    * */
    @PostMapping("/add")
    public void insert(@RequestBody ExecutiveReqDto executiveReqDto){
        executiveService.insert(executiveReqDto);
    }
}
