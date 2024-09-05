package com.sm.SocialMedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.Response.AuthResponse;
import com.sm.SocialMedia.dto.LoginDto;
import com.sm.SocialMedia.dto.UsersRegisterDto;
import com.sm.SocialMedia.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService aService;
	
	@PostMapping("/signUp")
	public AuthResponse registerUser(@RequestBody UsersRegisterDto udto) {
		AuthResponse res = aService.registerUser(udto);
		return  res;
	}
	
	@PostMapping("/signIn")
	public AuthResponse loginUser(@RequestBody LoginDto ldto) {
		AuthResponse res = aService.LoginUser(ldto);
		return res;
	}
}
