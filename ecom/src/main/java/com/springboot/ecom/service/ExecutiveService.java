package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.ExecutiveReqDto;
import com.springboot.ecom.enums.JobTitle;
import com.springboot.ecom.enums.Role;
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
        // Step 1: Fetch User details from dto
            String username = executiveReqDto.username();
            String password = executiveReqDto.password();
            Role role = Role.EXECUTIVE;

        // Step 2: Save user in DB
            User user = new User(); // this user has no id
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            // After save, we get the user back with id attached to it
            user = userRepository.save(user); // this user has an id

        // Step 3: Fetch executive details from dto
            String name = executiveReqDto.name();
            JobTitle jobTitle = executiveReqDto.jobTitle();
            Executive executive = new Executive();
            executive.setName(name);
            executive.setJobTitle(jobTitle);

        // Step 4: Attach user to executive
            executive.setUser(user); // now executive has a user.

        // Step 5: Save executive in Db
            executiveRepository.save(executive);
    }
}
