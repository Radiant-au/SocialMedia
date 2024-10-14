package com.sm.SocialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.dto.ChatDto;
import com.sm.SocialMedia.dto.ChatRequestDto;
import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.service.Chatservice;
import com.sm.SocialMedia.service.userService;

@RestController
@RequestMapping("api/chats")
public class ChatController {

	@Autowired
	private Chatservice cService;
	
	@Autowired
	private userService uService;
	
	@PostMapping
	public ChatDto createChat(@RequestHeader("Authorization") String jwt , @RequestBody ChatRequestDto crdto){
		UsersDto reqUser = uService.getUserfromToken(jwt);
		ChatDto chat = cService.createChat(reqUser.getId(), crdto.getUserId());
		return chat;
	}
	
	@GetMapping
	public List<ChatDto> findUserChat(@RequestHeader("Authorization") String jwt){
		UsersDto reqUser = uService.getUserfromToken(jwt);
		List<ChatDto> chatList = cService.findUsersChat(reqUser.getId());
		
		return chatList;
	}
}
