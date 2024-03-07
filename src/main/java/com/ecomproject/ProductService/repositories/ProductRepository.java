package com.ecomproject.ProductService.repositories;

import com.ecomproject.ProductService.dto.GenericProductDto;
import com.ecomproject.ProductService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Override
    Optional<Product> findById(UUID uuid);


//    @Query(value = "select * from product",nativeQuery = true)
//    List<Object[]> findAllprod();

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);


//    @Override
//    <S extends Product> S save(S entity);
}
