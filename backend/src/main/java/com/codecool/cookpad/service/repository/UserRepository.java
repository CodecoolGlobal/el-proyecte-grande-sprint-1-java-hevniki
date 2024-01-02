package com.codecool.cookpad.service.repository;

import com.codecool.cookpad.model.entity.IngredientType;
import com.codecool.cookpad.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}