package com.hostmdy.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.review.domain.Admin;
import com.hostmdy.review.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@GetMapping("/authenticate")
	public String authenticationForm(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "admin/login-form";
	}
	
	@PostMapping("/authenticate")
	public String authenticateAdmin(@ModelAttribute Admin admin, HttpServletRequest request) {
		Boolean authentication = adminService.authenticateAdmin(admin);
		if(!authentication) {
			return "admin/login-form";
		}
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin);
		session.setMaxInactiveInterval(3600 * 3);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logoutAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

}
