package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;


@Validated
public class Product {
    private Long id;
    @NotBlank(message = "Имя не может быть пустым")
    @Length(max = 255, message = "Название должно содержать не более 255 символов")
    private String name;
    @Length(max = 4096, message = "Описание должно содержать не более 4096 символов")
    private String description;
    private double price;
    private int amount;
    private boolean available;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
