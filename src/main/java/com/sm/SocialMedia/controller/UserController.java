package com.sm.SocialMedia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.service.userService;

@RestController
public class UserController {
	
	private userService uService;
	
	public UserController(userService uService) {
		this.uService = uService;
	}
	
	@GetMapping("/api/users")
	public List<UsersDto> getAllUser(){
		List<UsersDto> getAlluser = uService.getAllUser();
		return getAlluser;
	}
	
	@PostMapping("users")
	public UsersDto registerUser(@RequestBody UsersDto udto) {
		UsersDto savedUser = uService.registerUser(udto);
		return  savedUser;
	}
	
	@GetMapping("api/users/email")
	public UsersDto findByEmail(@RequestParam String email) {
		UsersDto user = uService.findByEmail(email);
		return user;
	}
		
	@GetMapping("api/users/{id}")
	public UsersDto getUserbyId(@PathVariable Long id) {
		UsersDto getUser = uService.getUserbyId(id);
		return getUser;
	}
	
	@PutMapping("api/users/{id}")
	public UsersDto UpdateUser(@PathVariable Long id , @RequestBody UsersDto udto) {
		UsersDto updateUser = uService.UpdateUser(id, udto);
		return updateUser;
	}
	
	@PostMapping("api/users/{id1}/{id2}")
	public UsersDto UpdateUser(@PathVariable Long id1 , @PathVariable Long id2) {
		UsersDto user = uService.followUser(id1, id2);
		return user;
	}
	
	@GetMapping("api/users/search")
	public List<UsersDto> searchUser(@RequestParam String query){
		List<UsersDto> getAllUser =uService.searchUser(query);
		return getAllUser;
	}

}
