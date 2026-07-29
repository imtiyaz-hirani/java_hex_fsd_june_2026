package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.PassengerReqDto;
import com.springboot.ecom.model.Passenger;
import com.springboot.ecom.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger")
@CrossOrigin(origins = "http://localhost:5173")
public class PassengerController {

    private final PassengerService passengerService;

    /* Fetching all passengers with pagination */
    @GetMapping("/all")
    public List<Passenger> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size){
        return passengerService.getAll(page,size);
    }

    @PostMapping("/add")
    public void add( @Valid  @RequestBody PassengerReqDto passengerReqDto){
        passengerService.add(passengerReqDto);
    }
}
