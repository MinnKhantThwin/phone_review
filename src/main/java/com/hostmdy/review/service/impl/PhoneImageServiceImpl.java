package com.hostmdy.review.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.repository.PhoneRepository;
import com.hostmdy.review.service.PhoneImageService;

@Service
public class PhoneImageServiceImpl implements PhoneImageService {
	
	private final PhoneRepository phoneRepository;

	public PhoneImageServiceImpl(PhoneRepository phoneRepository) {
		super();
		this.phoneRepository = phoneRepository;
	}

	@Override
	public void saveImageToDB(Long phoneId, MultipartFile imageFile) throws IOException  {
		// TODO Auto-generated method stub
		Optional<Phone> phoneOpt = phoneRepository.findById(phoneId);
		if(phoneOpt.isEmpty()) {
			throw new NullPointerException("Phone can't be found");
		}
		Phone phone = phoneOpt.get();
		byte[] imageBytes = imageFile.getBytes();
		
		phone.setImage(imageBytes);
		phoneRepository.save(phone);
	}

	@Override
	public byte[] getImageByPhone(Phone phone) {
		// TODO Auto-generated method stub
		Optional<Phone> phoneOpt = phoneRepository.findById(phone.getId());
		if(phoneOpt.isEmpty()) {
			throw new NullPointerException("Phone can't be found");
		}
		return phoneOpt.get().getImage();
	}

}
