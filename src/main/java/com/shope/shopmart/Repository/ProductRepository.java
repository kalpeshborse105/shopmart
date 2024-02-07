package com.shope.shopmart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shope.shopmart.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByManufacturer(String manufacturer);

    List<Product> findByPriceLessThan(Double price);
    // Page<Product> findByPriceLessThan(Double price, Pageable page);

    // MySQL query for manual sorting
    @Query(value = "select * from product order by product_name", nativeQuery = true)
    List<Product> sortByName();
}
