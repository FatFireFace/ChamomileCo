package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody Product product) {
        product.setId(idCounter++);
        if (product.getPrice() < 0)
            product.setPrice(0);
        if (!product.isAvailable())
            product.setAvailable(false);
        products.add(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = getProductById(id);
        if (product != null) {
            if (updatedProduct.getName() != null)
                product.setName(updatedProduct.getName());
            if (updatedProduct.getDescription() != null)
                product.setDescription(updatedProduct.getDescription());
            if (updatedProduct.getPrice() >= 0)
                product.setPrice(updatedProduct.getPrice());
            product.setAvailable(updatedProduct.isAvailable());
        }
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}