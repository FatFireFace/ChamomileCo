package com.example.demo;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Entity
@Table(name = "products")
@Validated
public class Product extends Identifiable{

    @Column(name = "description")
    @Length(max = 4096, message = "Описание должно содержать не более 4096 символов")
    private String description;
    @Column(name = "price")
    private double price = 0.0;
    @Column(name = "available")
    private boolean available;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shipment> shipments;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Selling> sellings;

    public int getAvailableAmount() {
        int shippedAmount = shipments.stream().mapToInt(Shipment::getAmount).sum();
        int soldAmount = sellings.stream().mapToInt(Selling::getAmount).sum();
        int availableAmount = shippedAmount - soldAmount;
        if (availableAmount > 0){
            return availableAmount;
        }
        else{
            setAvailable(false);
            return 0;
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
