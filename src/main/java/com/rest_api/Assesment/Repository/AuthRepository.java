package com.rest_api.Assesment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.Assesment.Model.User;

public interface AuthRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
