package com.leonardo.productreviewer.inputs;

import com.leonardo.productreviewer.models.enums.Type;

public record PropertyInput(
        String name,
        String defaultValue,
        Type type,
        String categoryId
) {
}
