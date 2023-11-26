package com.hostmdy.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hostmdy.review.service.PhoneService;

@Controller
public class HomeController {
	
	private final PhoneService phoneService;

	public HomeController(PhoneService phoneService) {
		super();
		this.phoneService = phoneService;
	}
	
	@GetMapping({"/", "/index", "/home"})
	public String showHome(Model model) {
		model.addAttribute("phones", phoneService.getAllPhones());
		return "index";
	}

}
