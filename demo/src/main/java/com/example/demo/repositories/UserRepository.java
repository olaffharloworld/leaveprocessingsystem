package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User,String>{
	@Query("SELECT u from User u WHERE u.name=:name")
	public User getUserByUsername(@Param("name") String name);
}
