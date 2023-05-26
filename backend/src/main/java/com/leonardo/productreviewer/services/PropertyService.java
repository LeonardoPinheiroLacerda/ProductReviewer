package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.inputs.PropertyInput;
import com.leonardo.productreviewer.models.Property;
import com.leonardo.productreviewer.repositories.CategoryRepository;
import com.leonardo.productreviewer.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record PropertyService(PropertyRepository repository, CategoryRepository categoryRepository) {
    public Property createProperty(PropertyInput propertyInput) {
        Property property = Property.builder()
                .type(propertyInput.type())
                .name(propertyInput.name())
                .defaultValue(propertyInput.defaultValue())
                .category(
                        categoryRepository
                                .findById(UUID.fromString(propertyInput.categoryId()))
                                .orElse(null)
                )
                .build();

        return repository.save(property);

    }
}
