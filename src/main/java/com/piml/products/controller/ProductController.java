package com.piml.products.controller;

import com.piml.products.dto.ProductDTO;
import com.piml.products.entity.Product;
import com.piml.products.interfaces.CategoryENUM;
import com.piml.products.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Product")
@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Register new Product")
    @PostMapping("/fresh-products")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO dto) {
        Product product = dto.map();
        ProductDTO createdProduct = ProductDTO.map(productService.create(product));
        return new ResponseEntity<ProductDTO>(createdProduct, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Find Product by ID")
    @GetMapping("/fresh-products/v1/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        Product product = productService.getById(id);
        ProductDTO convertedProduct = ProductDTO.map(product);
        return ResponseEntity.ok(convertedProduct);
    }

    @ApiOperation(value = "List all products optionally by IDs")
    @GetMapping("/fresh-products/v1")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(name = "products", required = false) List<Long> productIds)  {
        List<Product> productList = productService.getAllProducts(productIds);
        return ResponseEntity.ok(ProductDTO.map(productList));
    }

    @ApiOperation(value = "Find Products by category")
    @GetMapping("/fresh-products/v1/list")
    public ResponseEntity<List<ProductDTO>> getByCategory(@RequestParam(name = "category") CategoryENUM category) {
        List<Product> productList = productService.getByCategory(category);
        return ResponseEntity.ok(ProductDTO.map(productList));
    }

    @ApiOperation(value = "Search Products")
    @GetMapping("/api/v1/fresh-products/search")
    public ResponseEntity<List<ProductDTO>> findByName(@RequestParam(name = "name") String name) {
        List<Product> productList = productService.findByName(name);
        return ResponseEntity.ok(ProductDTO.map(productList));
    }

}
