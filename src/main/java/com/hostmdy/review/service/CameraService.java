package com.hostmdy.review.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.review.domain.Camera;

public interface CameraService {
	
	Camera saveCamera(Camera camera);
	Camera createCamera(Camera camera, Long phoneId);
	List<Camera> getAllCameraByPhone(Long phoneId);
	Optional<Camera> getCameraById(Long cameraId);
	void deleteCameraById(Long cameraId);

}
