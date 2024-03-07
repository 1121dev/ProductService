package com.ecomproject.ProductService.models;

import com.ecomproject.ProductService.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Category extends BaseModel {

    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Product> productList;
    public Category(String name) {
        this.name = name;
    }
}
