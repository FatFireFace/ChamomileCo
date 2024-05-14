package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "product", nullable = false)
    @NotBlank(message = "Имя не может быть пустым")
    @Length(max = 255, message = "Название должно содержать не более 255 символов")
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
