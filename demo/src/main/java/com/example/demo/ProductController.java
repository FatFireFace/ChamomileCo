package com.example.demo;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    private Long idCounter = 1L;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);

    }

    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product) {
        product.setId(idCounter++);
        if (product.getPrice() < 0)
            product.setPrice(0);
        if (!product.isAvailable())
            product.setAvailable(false);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        productService.createProduct(product);
        return ResponseEntity.created(location).body(String.format( "Товар %d успешно создан.", product.getId()));
    }

   @PutMapping("/{id}")
   public ResponseEntity<String> updateProduct(@PathVariable Long id, @Valid  @RequestBody Product updatedProduct) {
           Product existingProduct = productService.getProductById(id);

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

           productService.updateProduct(id, existingProduct);
           return ResponseEntity.ok("Продукт успешно обновлен");
       }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
            productService.deleteProduct(id);
            return ResponseEntity.status(200).body(String.format("Товар %d удален.", id));

    }

    @GetMapping("/search")
    public List<Product> searchProductByName(@PathParam("name") String name) {
        return productService.findProductByName(name);
    }

    @GetMapping("/search/to")
    public List<Product> searchProductByNameTo(@PathParam("name") String name, @PathParam("value") int value) {
        return productService.findProductByNameTo(name, value);
    }

    @GetMapping("/search/from-to")
    public List<Product> searchProductByNameFromTo(@PathParam("name") String name, @PathParam("from") int from, @PathParam("to") int to) {
        return productService.findProductByNameFromTo(name, from, to);
    }

    @GetMapping("/search/name")
    public List<Product> searchProductByExactName(@PathParam("name") String name) {
        return productService.findByName(name);
    }

    @GetMapping("/order/price")
    public List<Product> orderByPrice() {
        return productService.orderByPrice();
    }

    @GetMapping("/order/available")
    public List<Product> orderByAvailable() {
        return productService.orderByAvailable();
    }

    @GetMapping("/search/price-range")
    public List<Product> searchProductByPriceRange(@PathParam("from") double from, @PathParam("to") double to) {
        return productService.findByPriceInRange(from, to);
    }
}
