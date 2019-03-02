package com.usuarios.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usuarios.demo.model.Status;
import com.usuarios.demo.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	
	@Query("SELECT u FROM User u WHERE u.status = ?1")
    public List<User> find(@Param("status") Status status);
	
	
	
	
	
}
