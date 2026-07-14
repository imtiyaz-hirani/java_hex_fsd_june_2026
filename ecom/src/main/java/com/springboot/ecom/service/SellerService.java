package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.SellerReqDto;
import com.springboot.ecom.enums.Role;
import com.springboot.ecom.exception.ResourceNotFoundException;
import com.springboot.ecom.mapper.SellerMapper;
import com.springboot.ecom.mapper.UserMapper;
import com.springboot.ecom.model.Executive;
import com.springboot.ecom.model.Seller;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.ExecutiveRepository;
import com.springboot.ecom.repository.SellerRepository;
import com.springboot.ecom.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository  sellerRepository;
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;

    @Transactional
public void insert(long executiveId, @Valid SellerReqDto sellerReqDto) {
        // Step 1: Fetch Executive using given executiveId
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(()-> new ResourceNotFoundException("Executive id invalid.."));

        // Step 2: Fetch User details from dto and save it in DB
        User user =  UserMapper.convertDtoToEntity(     // this user is without id
                                            sellerReqDto.username(),
                                            sellerReqDto.password(),
                                            Role.SELLER);
        user = userRepository.save(user); // this user reference has an id attached.

        // Step 3: Fetch seller from dto
        Seller seller = SellerMapper.convertDtoToEntity(sellerReqDto);

        // Step 4: Attach user and executive to this seller
        seller.setUser(user);
        seller.setExecutive(executive);

        // Step 5: Save Seller in DB
        sellerRepository.save(seller);
    }
}
