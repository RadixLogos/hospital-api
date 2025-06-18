package com.radixlogos.hospitalapi.repositories;


import com.radixlogos.hospitalapi.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GeneralRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
    boolean existsByEmail(String email);
}
