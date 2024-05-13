package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nameFragment, '%')) " +
            "ORDER BY p.available DESC, p.price ASC, p.amount DESC")
    List<Product> findProductsByName(@Param("nameFragment") String partOfName);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nameFragment, '%')) " +
            "ORDER BY p.available DESC, p.price ASC, p.amount DESC")
    List<Product> findProductsByNameWithLimit(@Param("nameFragment") String partOfName, @Param("amount") int amount);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nameFragment, '%')) " +
            "ORDER BY p.available DESC, p.price ASC, p.amount DESC")
    List<Product> findProductsByNameWithLimitAndOffset(@Param("nameFragment") String partOfName, @Param("amount") int amount, @Param("from") int from);

    List<Product> findByName(String name);

    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> orderByPrice();

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceInRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query("SELECT p FROM Product p ORDER BY p.available DESC")
    List<Product> orderByAvailable();


}