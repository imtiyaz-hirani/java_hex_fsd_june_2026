package com.springboot.ecom.repository;

import com.springboot.ecom.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            select p
            from Product p
            where p.category.id = ?1
            """)
    List<Product> getByCategoryIdV1(long categoryId, Pageable pageable);

    // List<Product> getByCategoryIdV2(long categoryId);
}
