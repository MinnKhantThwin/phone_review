package com.hostmdy.review.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.repository.PhoneRepository;
import com.hostmdy.review.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService {

	private final PhoneRepository phoneRepository;

	public PhoneServiceImpl(PhoneRepository phoneRepository) {
		super();
		this.phoneRepository = phoneRepository;
	}

	@Override
	public Phone savePhone(Phone phone) {
		// TODO Auto-generated method stub
		return phoneRepository.save(phone);
	}

	@Override
	public List<Phone> getAllPhones() {
		// TODO Auto-generated method stub
		return (List<Phone>) phoneRepository.findAll();
	}

	@Override
	public Optional<Phone> getPhoneById(Long phoneId) {
		// TODO Auto-generated method stub
		return phoneRepository.findById(phoneId);
	}

	@Override
	public Optional<Phone> getPhoneByName(String name) {
		// TODO Auto-generated method stub
		return phoneRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public List<Phone> getPhoneByBrand(String brand) {
		// TODO Auto-generated method stub
		return phoneRepository.findByBrandContainingIgnoreCase(brand);
	}

	@Override
	public List<Phone> getPhoneByModel(String model) {
		// TODO Auto-generated method stub
		return phoneRepository.findByModelContainingIgnoreCase(model);
	}

	@Override
	public void deletePhoneById(Long phoneId) {
		// TODO Auto-generated method stub
		phoneRepository.deleteById(phoneId);
	}

	@Override
	public Set<Phone> searchedPhone(String keyword) {
		// TODO Auto-generated method stub
		Set<Phone> searchedPhone = new HashSet<>();

		Optional<Phone> searchedPhoneOpt = getPhoneByName(keyword);
		if (searchedPhoneOpt.isPresent()) {
			searchedPhone.add(searchedPhoneOpt.get());
			return searchedPhone;
		}

		String splitKeywords[] = keyword.split("", 10);
		for (String individual : splitKeywords) {
			List<Phone> phoneByBrand = getPhoneByBrand(individual);
			List<Phone> phoneByModel = getPhoneByModel(individual);
			searchedPhone.addAll(phoneByBrand);
			searchedPhone.addAll(phoneByModel);
		}
		
		return searchedPhone;
	}

}
