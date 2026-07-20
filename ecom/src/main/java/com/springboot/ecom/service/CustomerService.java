package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.CustomerDto;
import com.springboot.ecom.dto.response.CustomerRespDto;
import com.springboot.ecom.enums.Role;
import com.springboot.ecom.exception.ResourceNotFoundException;
import com.springboot.ecom.mapper.CustomerMapper;
import com.springboot.ecom.mapper.UserMapper;
import com.springboot.ecom.model.Customer;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.CustomerRepository;
import com.springboot.ecom.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public Customer add(CustomerDto customerDto) {
        // Fetch User entity from dto
        User user = UserMapper.convertDtoToEntity(customerDto.username(), customerDto.password(), Role.CUSTOMER);
        // encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // save user in db
        user = userRepository.save(user);

        // Convert dto to Entity
        Customer customer = CustomerMapper.mapDtoToEntity(customerDto);

        // Attach user to customer
        customer.setUser(user);

        // Give this dto to customer repository and save it in Db
        return customerRepository.save(customer);
    }

    public List<CustomerRespDto> getAll(int page, int size) {
        if(size == 0)
            throw new RuntimeException("Size has to be more than 0");

        // Work with Pagination
        Pageable pageable =  PageRequest.of(page,size);
        // Fetch all customer info
        List<Customer> list = customerRepository.fetchAll(pageable).getContent();

        // Convert List<Customer> to List<CustomerRespDto>
        // Trainer Tip: Convert Single Customer to Single CustomerRespDto
        return list
                .stream()
                .map(CustomerMapper::mapEntityToDto)
                .toList();
    }

    public CustomerRespDto getById(long id) {
        // If id is found, we return Dto
       Customer customer = customerRepository.fetchById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Customer id Invalid"));

       // Map Customer entity to dto
        return CustomerMapper.mapEntityToDto(customer);
    }

    public void delete(long id) {
        // Validate this id, to check if it exists
        Customer customer = customerRepository.fetchById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer id Invalid"));

        // In soft delete, we flip the isActive boolean from true to false
        customer.setActive(false);

        // Now save it in Db
        customerRepository.save(customer); //this becomes an edit op since id of this customer is present

    }

    public void deleteHard(long id) {
        // Validate this id, to check if it exists
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer id Invalid"));

        // Now delete it from Db
        customerRepository.deleteById(id);
    }

    public void update(long id, @Valid CustomerDto customerDto) {
        // Validate this id, to check if it exists
        Customer customerDB = customerRepository.fetchById(id)  // This customerDb comes form the database having id.
                .orElseThrow(()-> new ResourceNotFoundException("Customer id Invalid"));

        // Replace the fields of CustomerDB [old] with customerDto [New]
        customerDB.setName(customerDto.name());
        customerDB.setCity(customerDto.city());

        // Re-save it in DB
        customerRepository.save(customerDB);
    }
}
