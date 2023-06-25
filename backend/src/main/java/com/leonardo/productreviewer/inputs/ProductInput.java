package com.leonardo.productreviewer.inputs;

public record ProductInput(
        String description,
        Float price,
        String categoryId
) {
}
