package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.ExecutiveReqDto;
import com.springboot.ecom.dto.response.ExecutiveResDto;
import com.springboot.ecom.enums.JobTitle;
import com.springboot.ecom.model.Executive;
import com.springboot.ecom.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void insert(@Valid @RequestBody ExecutiveReqDto executiveReqDto){
        executiveService.insert(executiveReqDto);
    }

    @GetMapping("/by-jobTitle")
    public List<ExecutiveResDto> getByJobTitle(@RequestParam JobTitle jobTitle){
        return executiveService.getByJobTitle(jobTitle);
    }
}
