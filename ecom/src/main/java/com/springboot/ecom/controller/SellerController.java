package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.SellerReqDto;
import com.springboot.ecom.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    /*
    Body: {
        name: "",
        contact: "",
        city: "",
        username: "",
        password: ""
    }
    * */
    @PostMapping("/add")
    public void insert(Principal principal,
                       @Valid  @RequestBody SellerReqDto sellerReqDto){
        String executiveUsername = principal.getName();
        sellerService.insert(executiveUsername,sellerReqDto);
    }

    @DeleteMapping("/de-activate")
    public void deactivateSeller(Principal principal){
        String sellerUsername = principal.getName();
        sellerService.deactivateSeller(sellerUsername);
    }
}
