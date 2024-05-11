package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private Map<Long, Product> products = new HashMap<>();
    private Long idCounter = 1L;

    @GetMapping
    public Map<Long, Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return  products.values().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product) {
        product.setId(idCounter++);
        if (product.getPrice() < 0)
            product.setPrice(0);
        if (!product.isAvailable())
            product.setAvailable(false);
        products.put(product.getId(), product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(String.format( "Товар %d успешно создан.", product.getId()));
    }

   @PutMapping("/{id}")
   public ResponseEntity<String> updateProduct(@PathVariable Long id,@Valid  @RequestBody Product updatedProduct) {
       if (products.containsKey(id)) {
           Product existingProduct = products.get(id);

           if (updatedProduct.getName() != null){
               existingProduct.setName(updatedProduct.getName());
           }
           if (updatedProduct.getDescription() != null){
               existingProduct.setDescription(updatedProduct.getDescription());
           }
           if (updatedProduct.getPrice() != 0.0){
               existingProduct.setPrice(updatedProduct.getPrice());
           }
           if (updatedProduct.isAvailable() != existingProduct.isAvailable()){
               existingProduct.setAvailable(updatedProduct.isAvailable());
           }

           products.put(id, existingProduct);

           return ResponseEntity.ok("Продукт успешно обновлен");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Продукт с id " + id + " не найден");
       }
   }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            return ResponseEntity.status(400).body(String.format("Товар %d не найден", id));
        } else {
            products.remove(id);
            return ResponseEntity.status(200).body(String.format("Товар %d удален.", id));
        }
    }
}