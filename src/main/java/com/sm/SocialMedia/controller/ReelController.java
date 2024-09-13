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

import com.sm.SocialMedia.dto.ReelDto;
import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.service.ReelService;
import com.sm.SocialMedia.service.userService;

@RestController
@RequestMapping("api/reels")
public class ReelController {
	
	@Autowired
	userService uService;
	
	@Autowired
	ReelService rService;

	@GetMapping("all")
	public ResponseEntity<List<ReelDto>> getAllReels(){
		List<ReelDto> reeldto = rService.findAllReel();
		return new ResponseEntity<>(reeldto , HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ReelDto> createReel(@RequestHeader("Authorization") String jwt ,@RequestBody ReelDto rdto){
		UsersDto udto = uService.getUserfromToken(jwt);
		ReelDto reeldto = rService.createReel(udto.getId(), rdto);
		return new ResponseEntity<>(reeldto , HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<ReelDto>> getUserReel(@RequestHeader("Authorization") String jwt){
		UsersDto udto = uService.getUserfromToken(jwt);
		List<ReelDto> reeldto = rService.findReelById(udto.getId());
		return new ResponseEntity<>(reeldto , HttpStatus.OK);
	}
}
