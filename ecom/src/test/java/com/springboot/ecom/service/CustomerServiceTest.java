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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private Customer customer2;
    private User user2;

    private Customer customer3;
    private User user3;

    @BeforeEach
    public void init(){
        user1 = new User(1L,"john@gmail.com", "john@123", Role.CUSTOMER,true);
        customer1 = new Customer(1L,"John Doe","hubli",true, user1);

        user2 = new User(2L,"jane@gmail.com", "jane@123", Role.CUSTOMER,true);
        customer2 = new Customer(2L,"Jane Doe","Mysore",true, user2);

        user3 = new User(3L,"jack@gmail.com", "jack@123", Role.CUSTOMER,true);
        customer3 = new Customer(3L,"Jack Doe","Ooty",true, user3);
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

    @Test
    public void getAllTest(){
        int page = 0;
        int size=2;
        Pageable pageable1 =  PageRequest.of(page,size);

        Page<Customer> pageCustomer = new PageImpl<>(List.of(customer1,customer2));
        when(customerRepository.fetchAll(pageable1)).thenReturn(pageCustomer);

        size=3;
        Pageable pageable2 =  PageRequest.of(page,size);
        pageCustomer = new PageImpl<>(List.of(customer1,customer2,customer3));

        when(customerRepository.fetchAll(pageable2)).thenReturn(pageCustomer);

        Assertions.assertEquals(2 , customerService.getAll(0,2).size());
        Assertions.assertEquals(3 , customerService.getAll(0,3).size());
        Assertions.assertThrows(RuntimeException.class, ()-> customerService.getAll(0,0));

        verify(customerRepository , times(1)).fetchAll(pageable1);
        verify(customerRepository , times(1)).fetchAll(pageable2);
    }

}
