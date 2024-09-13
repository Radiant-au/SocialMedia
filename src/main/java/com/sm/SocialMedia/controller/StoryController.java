package com.sm.SocialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.dto.StoryDto;
import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.service.StoryService;
import com.sm.SocialMedia.service.userService;

@RestController
@RequestMapping("api/story")
public class StoryController {

	@Autowired
	StoryService sService;
	
	@Autowired
	userService uService;
	
	@PostMapping
	public ResponseEntity<StoryDto> addStory(@RequestHeader("Authorization")String jwt , @RequestBody StoryDto sdto){
		UsersDto user = uService.getUserfromToken(jwt);
		StoryDto storydto = sService.createStory(user.getId(), sdto);
		
		return new ResponseEntity<>(storydto , HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<StoryDto>> getUserStory(@RequestHeader("Authorization")String jwt ){
		UsersDto user = uService.getUserfromToken(jwt);
		List<StoryDto> storydto = sService.getStoryByuserId(user.getId());
		
		return new ResponseEntity<>(storydto , HttpStatus.ACCEPTED);
	}
	
}
