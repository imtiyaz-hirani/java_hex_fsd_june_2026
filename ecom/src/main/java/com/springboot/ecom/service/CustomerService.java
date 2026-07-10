package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.CustomerDto;
import com.springboot.ecom.dto.response.CustomerRespDto;
import com.springboot.ecom.exception.ResourceNotFoundException;
import com.springboot.ecom.mapper.CustomerMapper;
import com.springboot.ecom.model.Customer;
import com.springboot.ecom.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public Customer add(CustomerDto customerDto) {
        // Convert dto to Entity
        Customer customer = customerMapper.mapDtoToEntity(customerDto);
        // Give this dto to customer repository and save it in Db
        return customerRepository.save(customer);
    }

    public List<CustomerRespDto> getAll(int page, int size) {
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
}
