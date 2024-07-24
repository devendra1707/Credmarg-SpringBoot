package com.credmarg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credmarg.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailIgnoreCase(String email);

}
