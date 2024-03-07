package com.ecomproject.ProductService.dto;

import com.ecomproject.ProductService.models.Category;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericProductDto {
    private UUID id;
    private String title;
    private String description;

    private String categoryName;
    private double price;
    private String image;
//    @JsonCreator
//    public GenericProductDto(@JsonProperty("id") UUID id,
//                             @JsonProperty("title") String title,
//                             @JsonProperty("description") String description,
//                             @JsonProperty("category") Category categoryName,
//                             @JsonProperty("price") double price,
//                             @JsonProperty("image") String image) {
//        this.id = id; // Keep the provided id or set it to null if not provided
//        this.title = title;
//        this.description = description;
//        this.categoryobj = categoryName; // Assuming Category has a constructor that takes a String parameter
//        this.price = price;
//        this.image = image;
//    }
}
