package com.project.LoginRegistration.controllers;

import com.project.LoginRegistration.models.User;
import com.project.LoginRegistration.services.UserService;
import com.project.LoginRegistration.validators.UserValidator;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Users{
	private UserService userService;
	private UserValidator userValidator;

	public Users(UserService userService, UserValidator userValidator){
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	@RequestMapping("/register")
	public String registerForm(@Valid @ModelAttribute("user") User user){
		return "registrationPage";
	}

	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult res, Model model, HttpSession session) {
		userValidator.validate(user, res);
		if(res.hasErrors()) {
			return "registrationPage";
		}
		userService.saveWithUserRole(user);
		return "redirect:/login";
	}

	@RequestMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model){
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		return "loginPage";
	}

	@RequestMapping(value = {"/", "/dashboard"})
	public String home(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("currentUser", userService.findByUsername(username));
		return "homePage";
	}
}
