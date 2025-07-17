package com.postech.challenge_01.repositories.user;
import com.postech.challenge_01.domains.User;
import com.postech.challenge_01.repositories.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
    boolean updatePassword(Long id, String password);
}