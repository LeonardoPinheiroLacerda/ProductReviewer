package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.exceptions.ObjectNotFoundException;
import com.leonardo.productreviewer.inputs.ProductInput;
import com.leonardo.productreviewer.inputs.ProductPropertyValueInput;
import com.leonardo.productreviewer.models.Category;
import com.leonardo.productreviewer.models.Product;
import com.leonardo.productreviewer.models.ProductProperty;
import com.leonardo.productreviewer.models.Property;
import com.leonardo.productreviewer.repositories.ProductPropertyRepository;
import com.leonardo.productreviewer.repositories.ProductRepository;
import com.leonardo.productreviewer.utils.DataIntegrityUtils;
import com.leonardo.productreviewer.utils.UUIDUtils;
import graphql.com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record ProductService(
        ProductRepository repository,
        PropertyService propertyService,
        ProductPropertyRepository productPropertyRepository,
        CategoryService categoryService,
        UUIDUtils uuidUtils,
        DataIntegrityUtils dataIntegrityUtils

) implements CrudService<Product, UUID, ProductInput> {

    @Override
    public Product create(ProductInput productInput) {

        dataIntegrityUtils.checkNullOrEmptyAndThrowException(productInput.description(), "O campo descrição é obrigatório");
        dataIntegrityUtils.checkNullOrEmptyAndThrowException(productInput.categoryId(), "O campo categoryId é obrigatório");

        UUID categoryId = uuidUtils.parseFromString(productInput.categoryId());

        Category category = categoryService.getById(categoryId);

        final Product product = Product.builder()
                .price(productInput.price())
                .description(productInput.description())
                .category(category)
                .properties(Sets.newHashSet())
                .build();

        category.getProperties().forEach(property -> {
            product.getProperties().add(
                    ProductProperty.builder()
                            .product(product)
                            .property(property)
                            .value(property.getDefaultValue())
                            .build()
            );
        });

        repository.save(product);

        product.getProperties().forEach(productProperty -> productPropertyRepository.save(productProperty));

        return product;
    }

    @Override
    public Product update(UUID id, ProductInput productInput) {

        Product product = getById(id);

        if(!dataIntegrityUtils.checkNullOrEmpty(productInput.price()))           product.setPrice(productInput.price());
        if(!dataIntegrityUtils.checkNullOrEmpty(productInput.description()))     product.setDescription(productInput.description());

        if(!dataIntegrityUtils.checkNullOrEmpty(productInput.categoryId())) {
            UUID categoryId = uuidUtils.parseFromString(productInput.categoryId());
            product.setCategory(categoryService.getById(categoryId));
        }

        return repository.save(product);
    }

    @Override
    public UUID delete(UUID id) {
        repository.delete(this.getById(id));
        return id;
    }

    @Override
    public Product getById(UUID id) {
        uuidUtils.parseFromString(id.toString());

        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto de ID informado não pode ser encontrada."));
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product setPropertyValue(ProductPropertyValueInput productPropertyValueInput) {

        UUID productId = uuidUtils.parseFromString(productPropertyValueInput.productId());
        UUID propertyId = uuidUtils.parseFromString(productPropertyValueInput.propertyId());

        Product product = getById(productId);
        Property property = propertyService.getById(propertyId);

        ProductProperty productProperty = productPropertyRepository.findByProductAndProperty(product, property);

        productProperty.setValue(productPropertyValueInput.value());

        productPropertyRepository.save(productProperty);

        return getById(productId);
    }
}
