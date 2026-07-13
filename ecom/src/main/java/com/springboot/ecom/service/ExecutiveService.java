package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.ExecutiveReqDto;
import com.springboot.ecom.enums.JobTitle;
import com.springboot.ecom.enums.Role;
import com.springboot.ecom.mapper.ExecutiveMapper;
import com.springboot.ecom.mapper.UserMapper;
import com.springboot.ecom.model.Executive;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.ExecutiveRepository;
import com.springboot.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecutiveService {

    private final UserRepository userRepository;
    private final ExecutiveRepository executiveRepository;

    public void insert(ExecutiveReqDto executiveReqDto) {
        // Step 1: Fetch User details from dto and save it in DB
            User user = UserMapper.convertDtoToEntity(executiveReqDto.username(), executiveReqDto.password());
            // After save, we get the user back with id attached to it
            user = userRepository.save(user); // this user has an id

        // Step 2: Fetch executive details from dto
            Executive executive = ExecutiveMapper.convertDtoToEntity(executiveReqDto);

        // Step 3: Attach user to executive
            executive.setUser(user); // now executive has a user.

        // Step 4: Save executive in Db
            executiveRepository.save(executive);
    }
}
