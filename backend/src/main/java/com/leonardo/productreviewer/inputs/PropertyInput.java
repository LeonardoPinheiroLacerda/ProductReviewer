package com.leonardo.productreviewer.inputs;

import com.leonardo.productreviewer.models.Category;
import com.leonardo.productreviewer.models.enums.Type;
import jakarta.persistence.*;

public record PropertyInput(
        String name,
        String defaultValue,
        Type type,
        String categoryId
) {
}
