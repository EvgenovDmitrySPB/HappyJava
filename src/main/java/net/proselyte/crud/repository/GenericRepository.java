package net.proselyte.crud.repository;

import java.util.ArrayList;
import java.util.List;

public interface GenericRepository<T, ID> {
    void save(T t);

    T getById(ID id);

    void deleteById(ID id);

    List<T> getAll();

    void update(T t);
}
