package com.piml.products.exception.handler;

import com.piml.products.dto.ErrorMessageDTO;
import com.piml.products.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductValidationHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFound(ProductNotFoundException exception){
        ErrorMessageDTO error = new ErrorMessageDTO(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
