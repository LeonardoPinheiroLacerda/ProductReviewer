package com.leonardo.productreviewer.repositories;

import com.leonardo.productreviewer.models.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductPropertyRepository extends JpaRepository<ProductProperty, UUID> {
}
