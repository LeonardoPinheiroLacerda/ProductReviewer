package com.leonardo.productreviewer.models.PKs;

import com.leonardo.productreviewer.models.Product;
import com.leonardo.productreviewer.models.Property;
import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = {"product", "property"})
public class ProductPropertyPK implements Serializable {
    private Product product;
    private Property property;
}
