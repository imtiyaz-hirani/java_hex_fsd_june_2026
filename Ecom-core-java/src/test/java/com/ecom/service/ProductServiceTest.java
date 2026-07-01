package com.ecom.service;

import com.ecom.dto.FilterDto;
import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.Seller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ProductServiceTest {

    ProductService productService;
    List<Product> list;
    Seller seller1;
    Seller seller2;
    Seller seller3;
    Category catElectronics;
    Category catApparel;
    Category catGroceries;

    Product product1;
    Product product2;
    Product product3;
    Product product4;
    Product product5;

    @BeforeEach
    public void init(){
        productService = new ProductService(); //100X
        // Create Seller Objects
        seller1 = new Seller(1, "TechGadgets Ltd", "New York");
        seller2 = new Seller(2, "SportZone", "Chicago");
        seller3 = new Seller(3, "GreenBean Co", "Seattle");

        // Create Category Objects
        catElectronics = new Category(101, "Electronics", 1);
        catApparel = new Category(102, "Apparel", 2);
        catGroceries = new Category(103, "Groceries", 3);

        // Create Product Objects
        product1 = new Product(1, "Wireless Mouse", 29.99, "Ergonomic 2.4G mouse", 150, catElectronics, seller1);
        product2 = new Product(2, "Mechanical Keyboard", 89.99, "RGB backlit keyboard", 45, catElectronics, seller1);
        product3 = new Product(3, "Running Shoes", 79.95, "Lightweight breathable shoes", 80, catApparel, seller2);
        product4 = new Product(4, "Organic Coffee Beans", 14.50, "Dark roast 1kg bag", 200, catGroceries, seller3);
        product5 = new Product(5, "Gaming Headset", 49.99, "Surround sound mic headset", 60, catElectronics, seller1);

        list = new ArrayList<>(Arrays.asList(product1,product2, product3, product4, product5));
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

    @Test
    public void filterByCriteriaTest(){
        // Build your test data

        FilterDto dto = new FilterDto(
                List.of(catElectronics.getName(),catGroceries.getName() ),
                List.of(seller1.getName(),seller3.getName() ),
                40,
                80
        );
        List<Product> listExpected = List.of(product5);
        Assertions.assertEquals( listExpected ,productService.filterByCriteria(list, dto));

        dto = new FilterDto(
                List.of(catElectronics.getName(),catGroceries.getName() ),
                List.of(seller1.getName(),seller3.getName() ),
                50,
                80
        );
        listExpected = List.of();
        Assertions.assertEquals( listExpected ,productService.filterByCriteria(list, dto));

        dto = new FilterDto(
                List.of(),
                List.of(seller1.getName(),seller3.getName() ),
                20,
                80
        );
        listExpected = List.of(product1, product5);
        Assertions.assertEquals( listExpected ,productService.filterByCriteria(list, dto));

    }

    @Test
    public void getProductTitlesTest(){
        List<String> listTitles = List.of(
                product1.getTitle(),
                product2.getTitle(),
                product3.getTitle(),
                product4.getTitle(),
                product5.getTitle());

        Assertions.assertEquals(listTitles , productService.getProductTitles(list));
    }

    @Test
    public void getProductsForEachSellerTest(){
        Map<String, Integer> outMap = new HashMap<>();
        outMap.put(seller1.getName(), 3);
        outMap.put(seller2.getName(), 1);
        outMap.put(seller3.getName(), 1);

        Assertions.assertEquals(outMap , productService.getProductsForEachSeller(list));
    }
    @AfterEach
    public void afterTest(){
        productService = null; // Releasing the object
    }
}
