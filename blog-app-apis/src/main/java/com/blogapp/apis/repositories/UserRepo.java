package com.blogapp.apis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.apis.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	 Optional<User> findByEmail(String email);
}
