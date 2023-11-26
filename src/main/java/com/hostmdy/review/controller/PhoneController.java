package com.hostmdy.review.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.review.domain.Battery;
import com.hostmdy.review.domain.Body;
import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.domain.Platform;
import com.hostmdy.review.domain.Sensors;
import com.hostmdy.review.service.PhoneImageService;
import com.hostmdy.review.service.PhoneService;

@Controller
@RequestMapping("/phone")
public class PhoneController {

	private final PhoneService phoneService;
	private final PhoneImageService imageService;

	public PhoneController(PhoneService phoneService, PhoneImageService imageService) {
		super();
		this.phoneService = phoneService;
		this.imageService = imageService;
	}

	@GetMapping("/{phoneId}/show")
	public String showDetails(@PathVariable Long phoneId, Model model) {
		Optional<Phone> phoneOpt = phoneService.getPhoneById(phoneId);
		if (phoneOpt.isEmpty()) {
			throw new NullPointerException("Phone can't be found");
		}
		model.addAttribute("phone", phoneOpt.get());
		return "phone/phone-details";
	}

	@GetMapping("/new")
	public String createPhoneForm(Model model) {
		Phone phone = new Phone();
		phone.setPlatform(new Platform());
		phone.setBody(new Body());
		phone.setBattery(new Battery());
		model.addAttribute("phone", phone);
		model.addAttribute("sensors", Sensors.values());
		return "phone/add-phone";
	}

	@PostMapping("/new")
	public String createPhone(@ModelAttribute Phone phone) throws IOException {
		byte[] phoneImage = imageService.getImageByPhone(phone);
		Phone createdPhone = null;
		if (phoneImage != null) {
			phone.setImage(phoneImage);
			createdPhone = phoneService.savePhone(phone);
		} else {
			createdPhone = phoneService.savePhone(phone);
		}
		if (createdPhone == null) {
			throw new NullPointerException("phone is not created");
		}
		return "redirect:/";
	}

	@GetMapping("/{phoneId}/edit")
	public String showPhoneUpdateForm(@PathVariable Long phoneId, Model model) {
		Optional<Phone> phoneOpt = phoneService.getPhoneById(phoneId);
		if (phoneOpt.isEmpty()) {
			throw new NullPointerException("Phone is not found");
		}
		model.addAttribute("phone", phoneOpt.get());
		return "phone/add-phone";
	}

	@GetMapping("/{phoneId}/delete")
	public String deletePhone(@PathVariable Long phoneId) {
		phoneService.deletePhoneById(phoneId);
		return "redirect:/";
	}

	@PostMapping("/search")
	public String searchedPhone(@RequestParam String keyword, Model model) {
		Set<Phone> phones = phoneService.searchedPhone(keyword);
		model.addAttribute("phones", phones);
		return "index";
	}

}
