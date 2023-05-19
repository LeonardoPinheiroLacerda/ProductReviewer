package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public record PropertyService(PropertyRepository repository) {
}
