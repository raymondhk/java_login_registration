package com.project.LoginRegistration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.LoginRegistration.models.User;
import com.project.LoginRegistration.repositories.RoleRepository;
import com.project.LoginRegistration.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BCryptPasswordEncoder passEncoder;
		
	public UserService(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder passEncoder){
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passEncoder = passEncoder;
	}

	public void saveWithUserRole(User user) {
		user.setPassword(passEncoder.encode(user.getPassword()));
		user.setRoles(roleRepo.findByName("ROLE_USER"));
		userRepo.save(user);
	}

	public void saveUserWithAdminRole(User user) {
		user.setPassword(passEncoder.encode(user.getPassword()));
		user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
		userRepo.save(user);
	}

	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
