package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.PassengerReqDto;
import com.springboot.ecom.model.Passenger;
import com.springboot.ecom.repository.PassengerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public List<Passenger> getAll(int page, int size) {
        Pageable pageable =  PageRequest.of(page,size,Sort.by(Sort.Direction.DESC, "createdAt"));
        return passengerRepository.findAll(pageable).toList();
    }

    public void add(@Valid PassengerReqDto passengerReqDto) {
        // Step 1: Check for contact Uniqueness
         Optional<Passenger> optional=  passengerRepository.findByContact(passengerReqDto.contact());
         // if optional has a passenger , it means contact is already registered, throw exception
        if(optional.isPresent())
            throw new RuntimeException("Contact number already registered");

        Passenger passenger = new Passenger();
        passenger.setName(passengerReqDto.name());
        passenger.setContact(passengerReqDto.contact());

        passengerRepository.save(passenger);

    }
}
