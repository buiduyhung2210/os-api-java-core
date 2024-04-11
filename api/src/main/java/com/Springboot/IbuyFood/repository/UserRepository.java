package com.Springboot.IbuyFood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.IbuyFood.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
