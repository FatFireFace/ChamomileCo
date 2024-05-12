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
    List<Product> findProductsByName(@Param("nameFragment") String partOfName); /* вернуть все продукты, содержащие переданный фрагмент названия
                                                                                приведение к нижнему регистру и обрамление элементами поиска
                                                                                должны исключить sql инъекции
                                                                                */

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nameFragment, '%')) " +
            "ORDER BY p.available DESC, p.price ASC, p.amount DESC"+"LIMIT :amount")
    List<Product> findProductByName(@Param("nameFragment") String partOfName, @Param("amount") int amount);//вернуть первые amount продуктов, содержащие переданный фрагмент


    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nameFragment, '%')) " +
            "ORDER BY p.available DESC, p.price ASC, p.amount DESC"+"LIMIT :amount OFFSET :from")
    List<Product> findProductByName(@Param("nameFragment") String partOfName, @Param("amount") int amount, @Param("from") int from);
                //вернуть первые amount продуктов, содержащие переданный фрагмент, начиная с элемента from+1

    List<Product> findByName(String name);//механизм генерации запросов jpa ориентируется на ключевые слова

   @Query("SELECT * FROM products ORDER BY price")
    List<Product> orderByPrice();
   @Query("SELECT * FROM products ORDER BY price DESC")
   List<Product> orderByPriceDESC();

   @Query("SELECT * FROM products WHERE price BETWEEN :min_price AND :max_price")
   List<Product> findByPriceInRange(@Param("min_price") double minPrice, @Param("max_price") double maxPrice);


}