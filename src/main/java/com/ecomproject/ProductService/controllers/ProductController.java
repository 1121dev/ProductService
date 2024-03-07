package com.ecomproject.ProductService.controllers;

import com.ecomproject.ProductService.dto.GenericProductDto;
import com.ecomproject.ProductService.exceptions.BadRequestException;
import com.ecomproject.ProductService.exceptions.NotFoundException;
import com.ecomproject.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id")String id) throws NotFoundException
    {
        return new ResponseEntity<>(productService.getProductById(UUID.fromString(id)), HttpStatus.FOUND);
    }
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts()
    {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericProductDto> addNewProduct(@RequestBody GenericProductDto genericProductDto) throws BadRequestException
    {
        //GenericProductDto newProduct = productService.addProduct(genericProductDto);
        return new ResponseEntity<>(productService.addProduct(genericProductDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") String id) throws NotFoundException
    {
        return new ResponseEntity<>(productService.deleteProduct(UUID.fromString(id)), HttpStatus.FOUND);
    }
}
