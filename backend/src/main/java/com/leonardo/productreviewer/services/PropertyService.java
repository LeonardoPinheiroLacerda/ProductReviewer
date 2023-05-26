package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.exceptions.ObjectNotFoundException;
import com.leonardo.productreviewer.exceptions.UUIDException;
import com.leonardo.productreviewer.inputs.PropertyInput;
import com.leonardo.productreviewer.models.Property;
import com.leonardo.productreviewer.repositories.CategoryRepository;
import com.leonardo.productreviewer.repositories.PropertyRepository;
import com.leonardo.productreviewer.utils.UUIDUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record PropertyService(PropertyRepository repository, CategoryRepository categoryRepository, UUIDUtils uuidUtils) {
    public Property createProperty(PropertyInput propertyInput) {

        UUID id = uuidUtils.parseFromString(propertyInput.categoryId());

        Property property = Property.builder()
                .type(propertyInput.type())
                .name(propertyInput.name())
                .defaultValue(propertyInput.defaultValue())
                .category(
                        categoryRepository
                                .findById(id)
                                .orElseThrow(() -> new ObjectNotFoundException("Categoria de ID informado não pode ser encontrada."))
                )
                .build();

        return repository.save(property);

    }
}
