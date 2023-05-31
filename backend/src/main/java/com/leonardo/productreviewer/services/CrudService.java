package com.leonardo.productreviewer.services;

import java.util.List;

public interface CrudService<Type, PK, Input> {

    Type create(Input input);

    Type update(PK id, Input input);

    PK delete(PK id);

    Type getById(PK id);

    List<Type> getAll();
}
