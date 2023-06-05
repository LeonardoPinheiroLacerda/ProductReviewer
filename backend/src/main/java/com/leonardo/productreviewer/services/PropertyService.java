package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.exceptions.ObjectNotFoundException;
import com.leonardo.productreviewer.inputs.PropertyInput;
import com.leonardo.productreviewer.models.Category;
import com.leonardo.productreviewer.models.Property;
import com.leonardo.productreviewer.repositories.PropertyRepository;
import com.leonardo.productreviewer.utils.DataIntegrityUtils;
import com.leonardo.productreviewer.utils.UUIDUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record PropertyService(
        PropertyRepository repository,
        CategoryService categoryService,
        UUIDUtils uuidUtils,
        DataIntegrityUtils dataIntegrityUtils
) implements CrudService<Property, UUID, PropertyInput> {

    @Override
    public Property create(PropertyInput propertyInput) {

        dataIntegrityUtils.checkNullOrEmptyAndThrowException(propertyInput.categoryId(), "O campo categoryId é obrigatório.");
        dataIntegrityUtils.checkNullOrEmptyAndThrowException(propertyInput.name(), "O campo name é obrigatório.");
        dataIntegrityUtils.checkNullAndThrowException(propertyInput.type(), "O campo type é obrigatório.");
        dataIntegrityUtils.checkNullOrEmptyAndThrowException(propertyInput.defaultValue(), "O campo defaultValue é obrigatório.");

        UUID categoryId = uuidUtils.parseFromString(propertyInput.categoryId());
        
        Category category = categoryService.getById(categoryId);

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

        Property property = this.getById(id);

        if(!dataIntegrityUtils.checkNullOrEmpty(propertyInput.name()))           property.setName(propertyInput.name());
        if(!dataIntegrityUtils.checkNullOrEmpty(propertyInput.defaultValue()))   property.setDefaultValue(propertyInput.defaultValue());
        if(!dataIntegrityUtils.checkNull(propertyInput.type()))                  property.setType(propertyInput.type());

        if(!dataIntegrityUtils.checkNullOrEmpty(propertyInput.categoryId())) {
            UUID categoryId = uuidUtils.parseFromString(propertyInput.categoryId());
            property.setCategory(categoryService.getById(categoryId));
        }

        property = repository.save(property);
        return property;
    }

    @Override
    public UUID delete(UUID id) {
        repository.delete(this.getById(id));
        return id;
    }
}
