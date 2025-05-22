package com.postech.challenge_01.repositories;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, ID> {
    Optional<E> findById(ID id);
    List<E> findAll(int size, int offset);
    E save(E entity);
    E update(E entity, ID id);
    Integer delete(ID id);
}