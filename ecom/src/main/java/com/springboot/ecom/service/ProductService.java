package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.ProductReqDto;
import com.springboot.ecom.dto.response.OrderDto;
import com.springboot.ecom.dto.response.ProductResDto;
import com.springboot.ecom.dto.response.ProductResStatDto;
import com.springboot.ecom.dto.response.UploadDto;
import com.springboot.ecom.enums.ProductPriceFilter;
import com.springboot.ecom.exception.ResourceNotFoundException;
import com.springboot.ecom.mapper.OrderMapper;
import com.springboot.ecom.mapper.ProductMapper;
import com.springboot.ecom.model.Category;
import com.springboot.ecom.model.Product;
import com.springboot.ecom.model.Seller;
import com.springboot.ecom.repository.CategoryRepository;
import com.springboot.ecom.repository.ProductRepository;
import com.springboot.ecom.repository.SellerRepository;
import com.springboot.ecom.utility.UploadUtility;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UploadUtility uploadUtility;

    private static final String uploadPath = "D:/Java fsd June 2026 git/ecom-react-ui/public/images";

    public void insert(long sellerId,
                       long categoryId,
                       ProductReqDto productReqDto) {

        // Step 1: Fetch Seller from given sellerId
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow();

        // Step 2: Fetch Category from given categoryId
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category id invalid."));

        // Step 3: Map productReqDto to Product entity
        Product product = ProductMapper.convertDtoToEntity(productReqDto);

        // Step 4: Attach seller obj and category obj to product
        product.setSeller(seller);
        product.setCategory(category);

        // Step 5: Save product in DB
        productRepository.save(product);
    }

    public List<ProductResDto> getByCategoryId(long categoryId, int page, int size, ProductPriceFilter priceFilter) {

        Pageable pageable= null;

        if(priceFilter.equals(ProductPriceFilter.NO_SORT_PRICE)){
            // Step 0: Using page and size create the reference of Pageable
            pageable= PageRequest.of(page,size);
        }
        else{
            // Stp 0: Define Sort
            Sort sort = Sort.by(
                    priceFilter.equals(ProductPriceFilter.HIGH_TO_LOW_PRICE)?
                            Sort.Direction.DESC :
                            Sort.Direction.ASC, "price");

            pageable= PageRequest.of(page,size,sort);
        }
        List<Product> list = productRepository.findByCategoryId(categoryId,pageable);
        // Step 2: Use Mapper to convert List<Product> to List<ProductResDto> [Entity --> DTO]
        return  list
                .stream()
                .map(ProductMapper :: convertEntityToDto)
                .toList();

    }

    public List<ProductResStatDto> getProductForEachSeller() {
        return productRepository.getProductForEachSeller();
    }

    public List<OrderDto> getProductsPurchasedByCustomerUsername(String customerUsername, int page, int size) {
        // Step 0: Using page and size create the reference of Pageable
        Pageable pageable= PageRequest.of(page,size);

        List<OrderDto> list = productRepository.getProductsPurchasedByCustomerUsername(customerUsername,pageable);

        return list
                .stream()
                .map(OrderMapper :: processOrder)
                .toList();



    }

    public UploadDto uploadImage(long productId, MultipartFile imageFile) throws IOException {
        Product product =  productRepository.findById(productId)
                        .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

        uploadUtility.validateImage(imageFile);

        // Resolve the file using Nio : Convert the upload directory into a Path.
        Path uPathDir =  Paths.get(uploadPath);
        // Resolve the file into a path -- target
        Path filePath =  uPathDir.resolve(Objects.requireNonNull(imageFile.getOriginalFilename()));

        // upload the file
        Files.copy(imageFile.getInputStream(), filePath , StandardCopyOption.REPLACE_EXISTING);

        product.setImageUrl(filePath.toString());

        product = productRepository.save(product);

        return new UploadDto(
                product.getId(),
                product.getImageUrl(),
                imageFile.getOriginalFilename(),
                "File upload success"
        );
    }
}
