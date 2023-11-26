package com.hostmdy.review.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.review.crypto.PasswordEncoder;
import com.hostmdy.review.crypto.PasswordValidator;
import com.hostmdy.review.domain.Admin;
import com.hostmdy.review.repository.AdminRepository;
import com.hostmdy.review.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	private final AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}

	@Override
	public Optional<Admin> findAdminByName(String name) {
		// TODO Auto-generated method stub
		return adminRepository.findByName(name);
	}

	@Override
	public Admin createAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Admin dbAdmin = new Admin();
		dbAdmin.setName(admin.getName());
		try {
			dbAdmin.setPassword(PasswordEncoder.encode(admin.getPassword()));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saveAdmin(dbAdmin);
	}

	@Override
	public Boolean authenticateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Optional<Admin> adminOpt = findAdminByName(admin.getName());
		if(adminOpt.isEmpty()) {
			return false;
		}
		Admin adminTBD = adminOpt.get();
		try {
			if(!PasswordValidator.validatePassword(admin.getPassword(), adminTBD.getPassword())) {
				return false;
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
