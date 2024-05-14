package com.example.demo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Table(name = "selling")
@Entity
@Validated
public class Selling extends Identifiable{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column(name = "quantitySold")
    @NotNull(message = "укажите верное число проданных товаров")
    private int quantitySold;

    public int getAmount(){
        return quantitySold;
    }

    public void setAmount(int quantitySold){
        this.quantitySold = quantitySold;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
