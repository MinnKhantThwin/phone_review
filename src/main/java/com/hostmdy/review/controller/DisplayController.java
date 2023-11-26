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

import com.hostmdy.review.domain.Display;
import com.hostmdy.review.service.DisplayService;

@Controller
@RequestMapping("/display")
public class DisplayController {
	
	private final DisplayService displayService;

	public DisplayController(DisplayService displayService) {
		super();
		this.displayService = displayService;
	}
	
	@GetMapping("/{phoneId}/add")
	public String newDisplayForm(@PathVariable Long phoneId, Model model) {
		Display display = new Display();
		model.addAttribute("display", display);
		model.addAttribute("phoneId", phoneId);
		return "display/add-display";
	}
	
	@PostMapping("/add")
	public String createDisplay(@RequestParam Long phoneId, @ModelAttribute Display display) {
		displayService.createDisplay(display, phoneId);
		return "redirect:/phone/"+phoneId+"/show";
	}
	
	@GetMapping("/{displayId}/edit")
	public String updateCameraForm(@PathVariable Long displayId, Model model) {
		Optional<Display> displayOpt = displayService.getDisplayById(displayId);
		if(displayOpt.isEmpty()) {
			throw new NullPointerException("Display can't be found");
		}
		Display display = displayOpt.get();
		model.addAttribute("display", display);
		model.addAttribute("phoneId", display.getPhone().getId());
		return "display/add-display";
	}
	
	@GetMapping("/{displayId}/delete/{phoneId}")
	public String deleteDisplay(@PathVariable Long displayId, @PathVariable Long phoneId) {
		displayService.deleteDisplayById(displayId);
		return "redirect:/phone/"+phoneId+"/show";
	}

}
