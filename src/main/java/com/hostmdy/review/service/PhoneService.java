package com.hostmdy.review.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hostmdy.review.domain.Phone;

public interface PhoneService {
	
	Phone savePhone(Phone phone);
	List<Phone> getAllPhones();
	Optional<Phone> getPhoneById(Long phoneId);
	Optional<Phone> getPhoneByName(String name);
	List<Phone> getPhoneByBrand(String brand);
	List<Phone> getPhoneByModel(String model);
	void deletePhoneById(Long phoneId);
	Set<Phone> searchedPhone(String keyword);

}
