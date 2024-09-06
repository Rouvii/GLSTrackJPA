package org.example.persistence.daos.interfaces;

import java.util.Set;

public interface IDAO<T> {

    void create(T t);

    T getById(int id);
    Set<T> getAll();

    void update(T t);

    void delete(T t);
}
