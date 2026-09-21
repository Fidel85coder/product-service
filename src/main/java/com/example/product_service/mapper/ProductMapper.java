package com.example.product_service.mapper;
import com.example.product_service.domain.Product;
import com.example.product_service.dto.request.CreateProductRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.dto.request.UpdateProductRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest request) {

        LocalDateTime now = LocalDateTime.now();

        return new Product(
                UUID.randomUUID(),
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                now,
                now,
                null
        );
    }

    public ProductResponse toResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }

    public void updateEntity(
            Product product,
            UpdateProductRequest request
    ) {
        product.update(
                request.name(),
                request.description(),
                request.price(),
                request.stock()
        );
    }
}