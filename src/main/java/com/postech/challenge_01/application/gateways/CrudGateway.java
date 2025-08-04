package com.postech.challenge_01.application.gateways;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudGateway<E, ID> {
    E findById(ID id);
    List<E> findAll(Pageable pageable);
    E save(E entity);
    E update(E entity, ID id);
    void delete(ID id);
}