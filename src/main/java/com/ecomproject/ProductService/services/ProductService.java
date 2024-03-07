package com.ecomproject.ProductService.services;

import com.ecomproject.ProductService.dto.GenericProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public GenericProductDto getProductById(UUID id);
    public List<GenericProductDto> getAllProducts();
    public GenericProductDto addProduct(GenericProductDto genericProductDto);

    public GenericProductDto deleteProduct(UUID id);
}
