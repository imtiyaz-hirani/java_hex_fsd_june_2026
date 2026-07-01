package com.ecom.service;

import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceTest {

    ProductService productService;

    @BeforeEach
    public void init(){
        productService = new ProductService(); //100X
    }

    @Test
    public void demoTest(){
        Assertions.assertEquals(13,productService.sum(5,8));
        Assertions.assertEquals(3,productService.sum(-5,8));
        Assertions.assertEquals(-13,productService.sum(-5,-8));
        Assertions.assertEquals(8,productService.sum(0,8));
        Assertions.assertNotEquals(5,productService.sum(0,8));
    }

    @Test
    public void getProductsSortedByPriceTest(){
        // Prepare the input
        ProductDto product1 = new ProductDto(1, "Wireless Mouse", 29.99, "Electronics", "TechGadgets Ltd");
        ProductDto product2 = new ProductDto(2, "Running Shoes", 89.95, "Apparel", "SportZone");
        ProductDto product3 = new ProductDto(3, "Organic Coffee Beans", 14.50, "Groceries", "GreenBean Co");
        ProductDto product4 = new ProductDto(4, "Ergonomic Desk Chair", 199.00, "Furniture", "OfficeComfort");
        ProductDto product5 = new ProductDto(5, "Stainless Steel Water Bottle", 24.99, "Kitchenware", "EcoHydrate");
        List<ProductDto> list = new ArrayList<>(Arrays.asList(product1,product2, product3, product4, product5));

        // Prepare the expected Result
        List<ProductDto> listExpected = List.of(product3,product5, product1, product2, product4); // ASC Sort

        Assertions.assertEquals(listExpected,productService.getProductsSortedByPrice(list, SortDirection.ASC));
        Assertions.assertEquals(listExpected.reversed(),productService.getProductsSortedByPrice(list, SortDirection.DESC));

        Assertions.assertThrows(RuntimeException.class, ()-> productService.getProductsSortedByPrice(null, SortDirection.ASC));

        List<ProductDto> emptyList = new ArrayList<>();
        Assertions.assertEquals(emptyList,productService.getProductsSortedByPrice(emptyList, SortDirection.DESC));

    }

    @AfterEach
    public void afterTest(){
        productService = null; // Releasing the object
    }
}
