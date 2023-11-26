package com.hostmdy.review.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.review.domain.Display;
import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.repository.DisplayRepository;
import com.hostmdy.review.repository.PhoneRepository;
import com.hostmdy.review.service.DisplayService;

@Service
public class DisplayServiceImpl implements DisplayService {

	private final PhoneRepository phoneRepository;
	private final DisplayRepository displayRepository;

	public DisplayServiceImpl(DisplayRepository displayRepository, PhoneRepository phoneRepository) {
		super();
		this.phoneRepository = phoneRepository;
		this.displayRepository = displayRepository;
	}

	@Override
	public Display saveDisplay(Display display) {
		// TODO Auto-generated method stub
		return displayRepository.save(display);
	}

	@Override
	public Display createDisplay(Display display, Long phoneId) {
		// TODO Auto-generated method stub
		Optional<Phone> phoneOpt = phoneRepository.findById(phoneId);

		if (phoneOpt.isEmpty()) {
			throw new NullPointerException("Coundn't find the Phone");
		}
		
		Phone phone = phoneOpt.get();
		phone.getDisplays().add(display);
		display.setPhone(phone);
		
		return saveDisplay(display);
	}

	@Override
	public List<Display> getAllDisplaysByPhone(Long phoneId) {
		// TODO Auto-generated method stub
		Optional<Phone> phoneOpt = phoneRepository.findById(phoneId);

		if (phoneOpt.isEmpty()) {
			throw new NullPointerException("Coundn't find the Phone");
		}
		
		return displayRepository.findByPhone(phoneOpt.get());
	}

	@Override
	public Optional<Display> getDisplayById(Long displayId) {
		// TODO Auto-generated method stub
		return displayRepository.findById(displayId);
	}

	@Override
	public void deleteDisplayById(Long displayId) {
		// TODO Auto-generated method stub
		displayRepository.deleteById(displayId);
	}

}
