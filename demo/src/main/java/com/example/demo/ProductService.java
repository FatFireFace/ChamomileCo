package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setAvailable(updatedProduct.isAvailable());
        return productRepository.save(existingProduct);
    }

    public List<Product> findProductByName(String name){
        return productRepository.findProductsByName(name);
    }

    public List<Product> findProductByNameTo(String name, int value){
        return productRepository.findProductsByNameWithLimit(name, value);
    }

    public List<Product> findProductByNameFromTo(String name, int from, int to){
        return productRepository.findProductsByNameWithLimitAndOffset(name, to, from);
    }

    public List<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> orderByPrice(){
        return productRepository.orderByPrice();
    }

    public List<Product> orderByAvailable(){
        return productRepository.orderByAvailable();
    }

    public List<Product> findByPriceInRange(double from, double to){
        return productRepository.findByPriceInRange(from, to);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
