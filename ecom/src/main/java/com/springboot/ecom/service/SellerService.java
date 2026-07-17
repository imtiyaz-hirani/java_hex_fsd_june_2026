package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.SellerReqDto;
import com.springboot.ecom.enums.Role;
import com.springboot.ecom.exception.ResourceNotFoundException;
import com.springboot.ecom.mapper.SellerMapper;
import com.springboot.ecom.mapper.UserMapper;
import com.springboot.ecom.model.Executive;
import com.springboot.ecom.model.Product;
import com.springboot.ecom.model.Seller;
import com.springboot.ecom.model.User;
import com.springboot.ecom.repository.ExecutiveRepository;
import com.springboot.ecom.repository.ProductRepository;
import com.springboot.ecom.repository.SellerRepository;
import com.springboot.ecom.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository  sellerRepository;
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
public void insert(String executiveUsername, @Valid SellerReqDto sellerReqDto) {
        // Step 1: Fetch Executive using given username
        Executive executive = executiveRepository.findByUserUsername(executiveUsername);

        // Step 2: Fetch User details from dto and save it in DB
        User user =  UserMapper.convertDtoToEntity(     // this user is without id
                                            sellerReqDto.username(),
                                            sellerReqDto.password(),
                                            Role.SELLER);
        // encode password before saving in db
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivated(false);

        user = userRepository.save(user); // this user reference has an id attached.

        // Step 3: Fetch seller from dto
        Seller seller = SellerMapper.convertDtoToEntity(sellerReqDto);

        // Step 4: Attach user and executive to this seller
        seller.setUser(user);
        seller.setExecutive(executive);

        // Step 5: Save Seller in DB
        sellerRepository.save(seller);
    }

    @Transactional
    public void deactivateSeller(String sellerUsername) {
        // Step 1: Fetch Seller from repository using sellerUsername
        Seller seller = sellerRepository.getSellerByUsername(sellerUsername)
                .orElseThrow(()-> new ResourceNotFoundException("Seller username invalid"));

        // Step 2: Update the isActive and save it back in DB
        seller.getUser().setActivated(false);
        sellerRepository.save(seller);

        // Step 3: Fetch products that belong to this seller
        List<Product> list = productRepository.getProductBySellerId(seller.getId());

        // Step 4: update the stockCount to 0 and re-save it in DB
        list = list
                .stream()
                .peek(p-> p.setStockCount(0))
                .toList();

        productRepository.saveAll(list); // batch operation, will update all the products in ONE Transaction
    }
}
