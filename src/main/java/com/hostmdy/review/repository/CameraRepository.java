package com.hostmdy.review.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.review.domain.Camera;
import com.hostmdy.review.domain.Phone;

public interface CameraRepository extends CrudRepository<Camera, Long> {
	
	List<Camera> findByPhone(Phone phone);

}
