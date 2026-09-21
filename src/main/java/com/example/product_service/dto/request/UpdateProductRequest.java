package com.example.product_service.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductRequest(

        @NotBlank(message = "name.required")
        @Size(max = 150, message = "name.size <= 150")
        String name,

        @Size(max = 500, message = "description.size <= 500")
        String description,

        @NotNull(message = "price.required")
        @DecimalMin(value = "0.01", message = "price > 0")
        BigDecimal price,

        @NotNull(message = "stock.required")
        @PositiveOrZero(message = "stock >= 0")
        Integer stock
) {
}