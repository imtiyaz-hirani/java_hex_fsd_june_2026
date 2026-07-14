package com.springboot.ecom.repository;

import com.springboot.ecom.dto.response.ProductResStatDto;
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

     List<Product> findByCategoryId(long categoryId, Pageable pageable);

     @Query("""
             select s.name as sellerName, count(*) as numberOfProductsOwned
             from Product p JOIN p.seller s
             group by s.name
             """)
    List<ProductResStatDto> getProductForEachSeller();
}
/*
findByCategoryId:
findBy : select p from Product p where p.category.id=?1
* */