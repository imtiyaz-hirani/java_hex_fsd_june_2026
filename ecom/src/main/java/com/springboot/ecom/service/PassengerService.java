package com.springboot.ecom.service;

import com.springboot.ecom.model.Passenger;
import com.springboot.ecom.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public List<Passenger> getAll(int page, int size) {
        Pageable pageable =  PageRequest.of(page,size,Sort.by(Sort.Direction.DESC, "createdAt"));
        return passengerRepository.findAll(pageable).toList();
    }
}
