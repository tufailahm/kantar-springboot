package com.training.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.model.Credentials;

@RestController
public class HomeController {
	
	@Value("${welcome.message}")
	public String message;
	
	@Value("${welcome.compayName}")
	public String companyName;
	
	@Autowired
	Credentials credentials;
	
	
	
	@RequestMapping("/getCredentials")
	public Credentials getCredentials() {
		return credentials;
	}
	
	
	@RequestMapping("/")
	public String home() {
		return message;
	}
	
	@RequestMapping("/home")
	public String homePage() {
		return "Welcome to "+companyName;
	}
	

}
