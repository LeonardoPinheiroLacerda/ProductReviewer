package com.leonardo.productreviewer.services;

import java.util.List;

public interface CrudService<Type extends Object, PK extends Object, Input extends Object> {

    public Type create(Input input);

    public Type update(PK id, Input input);

    public PK delete(PK id);

    public Type getById(PK id);

    public List<Type> getAll();
}
