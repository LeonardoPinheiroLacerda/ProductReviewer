package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public record CategoryService(CategoryRepository repository) {
}
