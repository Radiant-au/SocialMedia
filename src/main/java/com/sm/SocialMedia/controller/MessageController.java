package com.sm.SocialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.dto.MessageDto;
import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.entity.Message;
import com.sm.SocialMedia.service.MessageService;
import com.sm.SocialMedia.service.userService;

@RestController
@RequestMapping("api/messages/")
public class MessageController {

	@Autowired
	private MessageService mService;
	
	@Autowired
	private userService uService;
	
	@PostMapping("chat/{chatId}")
	public MessageDto createMessage(@RequestBody Message req , @RequestHeader("Authorization") String jwt , @PathVariable Long chatId ) {
		UsersDto user = uService.getUserfromToken(jwt);
		MessageDto message = mService.createMessage(user.getId(), chatId, req);
		
		return message;
	}
	
	@GetMapping("chat/{chatId}")
	public List<MessageDto> findChatsMessage(@PathVariable Long chatId ) {
		List<MessageDto> message = mService.findChatMessages(chatId);
		
		return message;
	}
}
