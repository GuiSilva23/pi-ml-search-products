package com.piml.products.service;

import com.piml.products.entity.Product;
import com.piml.products.exception.ProductNotFoundException;
import com.piml.products.interfaces.CategoryENUM;
import com.piml.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product getById(Long id)  {
        return this.productRepository.getById(id);
    }

    public List<Product> getAllProducts(List<Long> productIds) {
        if(productIds != null) {
            return productRepository.findAllById(productIds);
        }
        List<Product> productList = productRepository.findAll();
        if(productList == null) {
            return new ArrayList<Product>();
        }
        return productList;
    }

    public List<Product> getByCategory(CategoryENUM category) {
       return productRepository.findByCategory(category.getCategoryDescription());
    }

    public List<Product> findByName(String name) {
        List<Product> productsFind = productRepository.findByNameIgnoreCaseContains(name)
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        if (productsFind.isEmpty()) {
            throw new ProductNotFoundException("Product not found!");
        }

        return productsFind;

    }
}
