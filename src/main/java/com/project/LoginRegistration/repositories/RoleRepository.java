package com.project.LoginRegistration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.LoginRegistration.models.Role;

@Repository 												
public interface RoleRepository extends CrudRepository<Role,Long>{
	List<Role> findAll();
	List<Role> findByName(String name);
}
