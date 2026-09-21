package com.example.product_service.mapper;

import com.example.product_service.domain.Category;
import com.example.product_service.dto.response.CategoryResponse;
import com.example.product_service.dto.request.CreateCategoryRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequest request) {

        return new Category(
                UUID.randomUUID(),
                request.name(),
                request.description()
        );
    }

    public CategoryResponse toResponse(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}