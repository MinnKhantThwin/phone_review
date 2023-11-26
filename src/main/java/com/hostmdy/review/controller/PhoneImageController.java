package com.hostmdy.review.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.service.PhoneImageService;
import com.hostmdy.review.service.PhoneService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/image")
public class PhoneImageController {
	
	private final PhoneService phoneService;
	private final PhoneImageService imageService;
	
	public PhoneImageController(PhoneService phoneService, PhoneImageService imageService) {
		super();
		this.phoneService = phoneService;
		this.imageService = imageService;
	}
	
	@GetMapping("/{phoneId}/add")
	public String uploadImageForm(@PathVariable Long phoneId, Model model) {
		model.addAttribute("phoneId", phoneId);
		return "phone/add-image";
	}
	
	@PostMapping("/{phoneId}/add")
	public String uploadImage(@PathVariable Long phoneId, @RequestParam MultipartFile phoneImage) throws IOException {
		imageService.saveImageToDB(phoneId, phoneImage);
		return "redirect:/phone/"+phoneId+"/show";
	}
	
	@GetMapping("/phone/{phoneId}/show")
	public void showPhoneImage(@PathVariable Long phoneId, HttpServletResponse response) {
		Optional<Phone> phoneOpt = phoneService.getPhoneById(phoneId);
		if(phoneOpt.isEmpty()) {
			throw new NullPointerException("Phone can't be found");
		}
		Phone phone = phoneOpt.get();
		byte[] imageBytes = phone.getImage();
		
		InputStream ls = new ByteArrayInputStream(imageBytes);
		response.setContentType("image/jpeg");
		try {
			IOUtils.copy(ls, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
