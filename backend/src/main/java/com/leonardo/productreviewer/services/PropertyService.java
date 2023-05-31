package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.exceptions.ObjectNotFoundException;
import com.leonardo.productreviewer.inputs.PropertyInput;
import com.leonardo.productreviewer.models.Category;
import com.leonardo.productreviewer.models.Property;
import com.leonardo.productreviewer.repositories.CategoryRepository;
import com.leonardo.productreviewer.repositories.PropertyRepository;
import com.leonardo.productreviewer.utils.UUIDUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record PropertyService(PropertyRepository repository, CategoryRepository categoryRepository, UUIDUtils uuidUtils) implements CrudService<Property, UUID, PropertyInput> {
    @Override
    public Property create(PropertyInput propertyInput) {

        UUID categoryId = uuidUtils.parseFromString(propertyInput.categoryId());
        
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria de ID informado não pode ser encontrada."));

        Property property = Property.builder()
                .type(propertyInput.type())
                .name(propertyInput.name())
                .defaultValue(propertyInput.defaultValue())
                .category(category)
                .build();

        return repository.save(property);

    }

    @Override
    public Property getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Propriedade de ID informado não pode ser encontrada."));
    }

    @Override
    public List<Property> getAll() {
        return repository.findAll();
    }

    @Override
    public Property update(UUID id, PropertyInput propertyInput) {
        return null;
    }

    @Override
    public UUID delete(UUID id) {
        return null;
    }
}
