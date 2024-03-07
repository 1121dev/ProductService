package com.ecomproject.ProductService.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Category category;
    private double price;
    private String image;
}
