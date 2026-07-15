package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.SellerReqDto;
import com.springboot.ecom.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/add/{executiveId}")
    public void insert(@PathVariable long executiveId,
                       @Valid  @RequestBody SellerReqDto sellerReqDto){
        sellerService.insert(executiveId,sellerReqDto);
    }

    @DeleteMapping("/de-activate")
    public void deactivateSeller(@RequestParam String sellerUsername){
        sellerService.deactivateSeller(sellerUsername);
    }
}
