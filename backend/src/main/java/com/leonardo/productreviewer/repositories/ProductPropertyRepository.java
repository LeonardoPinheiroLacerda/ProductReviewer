package com.leonardo.productreviewer.repositories;

import com.leonardo.productreviewer.models.Product;
import com.leonardo.productreviewer.models.ProductProperty;
import com.leonardo.productreviewer.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductPropertyRepository extends JpaRepository<ProductProperty, UUID> {

    @Query("SELECT obj FROM ProductProperty obj WHERE obj.product = ?1 AND obj.property = ?2")
    public Optional<ProductProperty> findByProductAndProperty(Product product, Property property);

}
