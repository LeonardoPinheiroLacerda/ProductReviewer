package com.leonardo.productreviewer.models;

import com.leonardo.productreviewer.models.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = {"id"})

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(name = "default_value", length = 32, nullable = false)
    private String defaultValue;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
