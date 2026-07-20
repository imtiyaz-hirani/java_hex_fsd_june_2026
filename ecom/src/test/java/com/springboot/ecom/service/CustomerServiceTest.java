package com.springboot.ecom.service;

import com.springboot.ecom.dto.response.CustomerRespDto;
import com.springboot.ecom.enums.Role;
import com.springboot.ecom.exception.ResourceNotFoundException;
import com.springboot.ecom.mapper.CustomerMapper;
import com.springboot.ecom.model.Customer;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.CustomerRepository;
import com.springboot.ecom.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private UserRepository userRepository;

    private Customer customer1;
    private User user1;

    @BeforeEach
    public void init(){
        user1 = new User(1L,"john@gmail.com", "john@123", Role.CUSTOMER,true);
        customer1 = new Customer(1L,"John Doe","hubli",true, user1);
    }

    @Test
    public void getByIdTestPresent(){
        /*
        when customerRepository.fetchById(5) gets called,
        it will ignore whatever comes out of the DB.
        and it will return customer1
        * */
        when(customerRepository.fetchById(5)).thenReturn(Optional.of(customer1));
        CustomerRespDto customerRespDto = CustomerMapper.mapEntityToDto(customer1);
        /*
        the actual call: customerService.getById(5) gives a Dto using CustomerMapper
        My Expected Call[which returns customer1 obj] is also converted to Dto using same Mapper
        * */
        Assertions.assertEquals(customerRespDto , customerService.getById(5));

        /*
        I want to ensure that my repository method gets call only once.
        Sometimes in Multithread/stream logic the DB calls happen multiple times which clearly effects performance
        * */
        verify(customerRepository, times(1)).fetchById(5);
    }

    @Test
    public void getByIdTestNotPresent(){
        // When id=10 is passed, we get Empty optional which means DB has no customer.
        when(customerRepository.fetchById(10)).thenReturn(Optional.empty());

        Assertions.assertEquals("Customer id Invalid" ,
                Assertions.assertThrows(ResourceNotFoundException.class, ()-> {
                    customerService.getById(10);
                }).getMessage() );

    }

}
