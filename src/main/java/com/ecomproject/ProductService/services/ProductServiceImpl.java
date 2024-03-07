package com.ecomproject.ProductService.services;

import com.ecomproject.ProductService.dto.GenericProductDto;
import com.ecomproject.ProductService.exceptions.BadRequestException;
import com.ecomproject.ProductService.exceptions.NotFoundException;
import com.ecomproject.ProductService.models.Category;
import com.ecomproject.ProductService.models.Product;
import com.ecomproject.ProductService.repositories.CategoryRepository;
import com.ecomproject.ProductService.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    private GenericProductDto convertProdToDto(Product product)
    {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setId(product.getUuid());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setCategoryName(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice());

        return genericProductDto;


//
//        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setDescription(product.getDescription());
//        genericProductDto.setId(product.getUuid());
//        genericProductDto.setImage(product.getImage());
//        genericProductDto.setTitle(product.getTitle());
//        genericProductDto.setCategory(product.getCategory());
//        genericProductDto.setPrice(product.getPrice());
//        return genericProductDto;
    }

    private UUID makeUUID(byte[] bytes)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }
    private Product convertDtoToProd(GenericProductDto genericProductDto)
    {
//        Product product = new Product();
//        product.setCategory(genericProductDto.getCategoryobj());
//        product.setTitle(genericProductDto.getTitle());
//        product.setPrice(genericProductDto.getPrice());
//        product.setImage(genericProductDto.getImage());
//        product.setDescription(genericProductDto.getDescription());
//
//        return product;

//        Product product = new Product();
////        Category category = categoryRepository;
////        category.setName(genericProductDto.getCategoryName());
////        product.setCategory(category);
//        Category category;
//        Optional<Category> categoryOptional = categoryRepository.findByName(genericProductDto.getCategoryName());
//        if(categoryOptional.isEmpty())
//         category = new Category(genericProductDto.getCategoryName(),null);
//        else
//            category = categoryOptional.get();
//
//        categoryRepository.save(category);
//        product.setTitle(genericProductDto.getTitle());
//        product.setPrice(genericProductDto.getPrice());
//        product.setImage(genericProductDto.getImage());
//        product.setDescription(genericProductDto.getDescription());
//        product.setCategory(category);
//        return product;

        Product product = new Product();
        Optional<Category> categoryOptional = categoryRepository.findByName(genericProductDto.getCategoryName());
        if(categoryOptional.isEmpty())
        {
            product.setCategory(new Category(genericProductDto.getCategoryName(),null));
        }
        else
        {
            product.setCategory(categoryOptional.get());
        }
        product.setTitle(genericProductDto.getTitle());
        product.setPrice(genericProductDto.getPrice());
        product.setImage(genericProductDto.getImage());
        product.setDescription(genericProductDto.getDescription());
        return product;
    }
    @Override
    public GenericProductDto getProductById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty())
            throw new NotFoundException("Product with id : "+id+" not available");

        Product reqProduct = productOptional.get();
        GenericProductDto genericProductDto = convertProdToDto(reqProduct);

        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
//        List<Object[]> result = productRepository.findAllprod();
//        List<GenericProductDto> reqAllProduct = new ArrayList<>();
//
//        if(result.isEmpty())
//            throw new NotFoundException("No products available");
//
//        for(Object[] row : result)
//        {
//            GenericProductDto genericProductDto = new GenericProductDto();
//            genericProductDto.setId(makeUUID((byte[])row[0]));
//            genericProductDto.setDescription((String) row[1]);
//            genericProductDto.setImage((String) row[2]);
//            genericProductDto.setPrice((double) row[3]);
//            genericProductDto.setTitle((String) row[4]);
////            genericProductDto.setCategoryobj((Category) row[5]);
//            UUID categoryId = makeUUID((byte[]) row[5]);
//            Category category = categoryRepository.findById(categoryId).orElse(null);
//            genericProductDto.setCategoryobj(category);
//            reqAllProduct.add(genericProductDto);
//        }
//
//
////        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
////
////        for(Product product : reqAllProduct)
////        {
////            genericProductDtoList.add(convertProdToDto(product));
////        }
//
//        //return genericProductDtoList;
//        return reqAllProduct;

        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty())
            throw new NotFoundException("No products available");

        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        for(Product product : productList)
        {
            genericProductDtoList.add(convertProdToDto(product));
        }

        return genericProductDtoList;
    }

    @Override
    public GenericProductDto addProduct(GenericProductDto genericProductDto) {
        Product newProduct = convertDtoToProd(genericProductDto);
        Product savedProduct = productRepository.save(newProduct);
        if(savedProduct==null)
            throw new BadRequestException("Something went Wrong, Please try again");

        genericProductDto = convertProdToDto(savedProduct);
        return genericProductDto;
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty())
            throw new NotFoundException("Product with id : "+id+" not available");

        Product reqProduct = productOptional.get();
        GenericProductDto genericProductDto = convertProdToDto(reqProduct);
        productRepository.deleteById(id);

        return genericProductDto;
    }
}
