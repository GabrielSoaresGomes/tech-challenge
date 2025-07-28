package com.postech.repositories;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, ID> {
    Optional<E> findById(ID id);
    List<E> findAll(int size, long offset);
    E save(E entity);
    Optional<E> update(E entity, ID id);
    Integer delete(ID id);
}