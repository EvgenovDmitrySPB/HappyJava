package net.proselyte.crud.repository;

import java.sql.SQLException;

public interface GenericRepository<T, ID> {

    void save(T t);

    T getById(ID id);

    void deleteById(ID id);

    void getAll();

    void update(T t);
}
