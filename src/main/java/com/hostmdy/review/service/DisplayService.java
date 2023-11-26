package com.hostmdy.review.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.review.domain.Display;

public interface DisplayService {
	
	Display saveDisplay(Display display);
	Display createDisplay(Display display, Long phoneId);
	List<Display> getAllDisplaysByPhone(Long phoneId);
	Optional<Display> getDisplayById(Long displayId);
	void deleteDisplayById(Long displayId);

}
