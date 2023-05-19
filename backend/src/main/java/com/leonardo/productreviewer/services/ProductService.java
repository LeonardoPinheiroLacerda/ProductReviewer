package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public record ProductService(ProductRepository repository) {
}
