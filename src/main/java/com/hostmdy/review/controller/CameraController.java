package com.hostmdy.review.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.review.domain.Camera;
import com.hostmdy.review.service.CameraService;

@Controller
@RequestMapping("/camera")
public class CameraController {
	
	private final CameraService cameraService;

	public CameraController(CameraService cameraService) {
		super();
		this.cameraService = cameraService;
	}
	
	@GetMapping("/{phoneId}/add")
	public String newCameraForm(@PathVariable Long phoneId, Model model) {
		Camera camera = new Camera();
		model.addAttribute("camera", camera);
		model.addAttribute("phoneId", phoneId);
		return "camera/add-camera";
	}
	
	@PostMapping("/add")
	public String createCamera(@RequestParam Long phoneId, @ModelAttribute Camera camera) {
		cameraService.createCamera(camera, phoneId);
		return "redirect:/phone/"+phoneId+"/show";
	}
	
	@GetMapping("/{cameraId}/edit")
	public String updateCameraForm(@PathVariable Long cameraId, Model model) {
		Optional<Camera> cameraOpt = cameraService.getCameraById(cameraId);
		if(cameraOpt.isEmpty()) {
			throw new NullPointerException("Camera can't be found");
		}
		Camera camera = cameraOpt.get();
		model.addAttribute("camera", camera);
		model.addAttribute("phoneId", camera.getPhone().getId());
		return "camera/add-camera";
	}
	
	@GetMapping("/{cameraId}/delete/{phoneId}")
	public String deleteCamera(@PathVariable Long cameraId, @PathVariable Long phoneId) {
		cameraService.deleteCameraById(cameraId);
		return "redirect:/phone/"+phoneId+"/show";
	}

}
