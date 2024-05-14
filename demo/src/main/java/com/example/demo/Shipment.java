package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Table(name = "shipment")
@Entity
@Validated
public class Shipment extends Identifiable{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    private Product product;

    @NotNull(message = "укажите корректное число товаров")
    @Column(name = "amount", nullable = false)
    private int amount;

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
