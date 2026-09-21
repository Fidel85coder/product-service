package com.example.product_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank(message = "name.required")
        @Size(max = 100, message = "name.size <= 100")
        String name,

        @Size(max = 300, message = "description.size <= 300")
        String description
) {}
