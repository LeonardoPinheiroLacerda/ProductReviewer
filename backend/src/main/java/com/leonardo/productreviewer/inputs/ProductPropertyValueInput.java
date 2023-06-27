package com.leonardo.productreviewer.inputs;

import java.util.UUID;

public record ProductPropertyValueInput(
        String productId,
        String propertyId,
        String value
) {
}