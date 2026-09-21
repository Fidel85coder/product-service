package com.example.product_service.service;

import com.example.product_service.domain.Category;
import com.example.product_service.domain.Product;
import com.example.product_service.dto.request.CreateProductRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.dto.request.UpdateProductRequest;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.repository.CategoryRepository;
import com.example.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper,
            CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ProductResponse create(CreateProductRequest request) {

        Category category = null;

        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(
                            () -> new RuntimeException("Category not found: " + request.categoryId())
                    );

        }

        Product product = productMapper.toEntity(request);

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        log.info(
                "Product created: {} with category: {}",
                savedProduct.getId(),
                category != null ? category.getId() : null
        );

        return productMapper.toResponse(savedProduct);
    }

    @Cacheable(value = "products", key = "#id")
    @Transactional(readOnly = true)
    public ProductResponse findById(UUID id) {

        log.info("CACHE MISS - Loading product from PostgreSQL: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + id)
                );

        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @CacheEvict(value = "products", key = "#id")
    @Transactional
    public ProductResponse update(
            UUID id,
            UpdateProductRequest request
    ) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + id)
                );

        productMapper.updateEntity(product, request);

        Product updatedProduct = productRepository.save(product);

        log.info("Product updated and cache invalidated: {}", id);

        return productMapper.toResponse(updatedProduct);
    }

    @CacheEvict(value = "products", key = "#id")
    @Transactional
    public void delete(UUID id) {

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found: " + id);
        }

        productRepository.deleteById(id);

        log.info("Product deleted and cache invalidated: {}", id);
    }
}