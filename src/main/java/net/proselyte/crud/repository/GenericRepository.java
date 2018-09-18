package net.proselyte.crud.repository;

import java.sql.SQLException;

public interface GenericRepository<T, ID> {

    void save(T t) throws SQLException;

    T getById(ID id) throws SQLException;

    void deleteById(ID id) throws SQLException;

    void getAll() throws SQLException;

    void update(ID id) throws SQLException;
}
