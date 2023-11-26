package com.hostmdy.review.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.review.domain.Phone;

public interface PhoneImageService {
	
	void saveImageToDB(Long phoneId, MultipartFile imageFile) throws IOException;
	byte[] getImageByPhone(Phone phone);

}
