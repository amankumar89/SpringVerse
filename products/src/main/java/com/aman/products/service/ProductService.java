package com.aman.products.service;

import com.aman.products.dto.CreateProductRequestDto;
import com.aman.products.dto.ProductResponseDto;
import com.aman.products.dto.UpdateProductRequestDto;
import com.aman.products.entity.Product;
import com.aman.products.exception.ResourceNotFoundException;
import com.aman.products.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ProductResponseDto createProduct(CreateProductRequestDto createDTO) {
        Product product = modelMapper.map(createDTO, Product.class);

        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map((p) -> modelMapper.map(p, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, UpdateProductRequestDto updateDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        if (updateDTO.getName() != null) {
            product.setName(updateDTO.getName());
        }
        if (updateDTO.getPrice() != null) {
            product.setPrice(updateDTO.getPrice());
        }
        if (updateDTO.getStock() != 0) {
            product.setStock(updateDTO.getStock());
        }
        if (updateDTO.getCategory() != null) {
            product.setCategory(updateDTO.getCategory());
        }
        if (updateDTO.getBrand() != null) {
            product.setBrand(updateDTO.getBrand());
        }
        if (updateDTO.getDescription() != null) {
            product.setDescription(updateDTO.getDescription());
        }

        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductResponseDto.class);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    public List<ProductResponseDto> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map((p) -> modelMapper.map(p, ProductResponseDto.class))
                .collect(Collectors.toList());
    }
}
