package com.sm.SocialMedia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.dto.UsersRegisterDto;
import com.sm.SocialMedia.dto.userUpdateDto;
import com.sm.SocialMedia.service.userService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private userService uService;
	
	public UserController(userService uService) {
		this.uService = uService;
	}
	
	@GetMapping
	public List<UsersDto> getAllUser(){
		List<UsersDto> getAlluser = uService.getAllUser();
		return getAlluser;
	}
	
	
	@GetMapping("email")
	public UsersDto findByEmail(@RequestParam String email) {
		UsersDto user = uService.findByEmail(email);
		return user;
	}
		
	@GetMapping("{id}")
	public UsersDto getUserbyId(@PathVariable Long id) {
		UsersDto getUser = uService.getUserbyId(id);
		return getUser;
	}
	
	@PutMapping
	public UsersDto UpdateUser(@RequestHeader("Authorization")String jwt , @RequestBody userUpdateDto udto) {
		UsersDto reqUser = uService.getUserfromToken(jwt);
		UsersDto updateUser = uService.UpdateUser(reqUser.getId(), udto);
		return updateUser;
	}
	
	@PutMapping("{id2}")
	public UsersDto FollowUser(@RequestHeader("Authorization")String jwt , @PathVariable Long id2) {
		UsersDto reqUser = uService.getUserfromToken(jwt);
		UsersDto user = uService.followUser(reqUser.getId() , id2);
		return user;
	}
	
	@GetMapping("search")
	public List<UsersDto> searchUser(@RequestParam String query){
		List<UsersDto> getAllUser =uService.searchUser(query);
		return getAllUser;
	}
	
	@GetMapping("profile")
	public UsersDto getUserByJwt(@RequestHeader("Authorization")String jwt) {
		UsersDto user = uService.getUserfromToken(jwt);
		return user;
	}

}
