package com.hostmdy.review.service;

import java.util.Optional;

import com.hostmdy.review.domain.Admin;

public interface AdminService {
	
	Admin saveAdmin(Admin admin);
	Optional<Admin> findAdminByName(String name);
	Admin createAdmin(Admin admin);
	Boolean authenticateAdmin(Admin admin);

}
