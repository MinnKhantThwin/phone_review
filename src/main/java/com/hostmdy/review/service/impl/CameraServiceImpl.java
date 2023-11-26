package com.hostmdy.review.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.review.domain.Camera;
import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.repository.CameraRepository;
import com.hostmdy.review.repository.PhoneRepository;
import com.hostmdy.review.service.CameraService;

@Service
public class CameraServiceImpl implements CameraService {

	private final PhoneRepository phoneRepository;
	private final CameraRepository cameraRepository;

	public CameraServiceImpl(CameraRepository cameraRepository, PhoneRepository phoneRepository) {
		super();
		this.phoneRepository = phoneRepository;
		this.cameraRepository = cameraRepository;
	}

	@Override
	public Camera saveCamera(Camera camera) {
		// TODO Auto-generated method stub
		return cameraRepository.save(camera);
	}

	@Override
	public Camera createCamera(Camera camera, Long phoneId) {
		// TODO Auto-generated method stub
		Optional<Phone> phoneOpt = phoneRepository.findById(phoneId);

		if (phoneOpt.isEmpty()) {
			throw new NullPointerException("Coundn't find the Phone");
		}

		Phone phone = phoneOpt.get();
		phone.getCameras().add(camera);
		camera.setPhone(phone);
		return saveCamera(camera);
	}

	@Override
	public List<Camera> getAllCameraByPhone(Long phoneId) {
		// TODO Auto-generated method stub
		Optional<Phone> phoneOpt = phoneRepository.findById(phoneId);

		if (phoneOpt.isEmpty()) {
			throw new NullPointerException("Coundn't find the Phone");
		}
		return cameraRepository.findByPhone(phoneOpt.get());
	}

	@Override
	public Optional<Camera> getCameraById(Long cameraId) {
		// TODO Auto-generated method stub
		return cameraRepository.findById(cameraId);
	}

	@Override
	public void deleteCameraById(Long cameraId) {
		// TODO Auto-generated method stub
		cameraRepository.deleteById(cameraId);
	}

}
