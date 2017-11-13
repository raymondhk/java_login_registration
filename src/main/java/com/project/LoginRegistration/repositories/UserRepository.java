package com.project.LoginRegistration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.LoginRegistration.models.User;

@Repository 												
public interface UserRepository extends CrudRepository<User,Long>{
	User findByUsername(String user);
}
