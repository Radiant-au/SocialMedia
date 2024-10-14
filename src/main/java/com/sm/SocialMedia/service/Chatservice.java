package com.sm.SocialMedia.service;

import java.util.List;

import com.sm.SocialMedia.dto.ChatDto;

public interface Chatservice {
	
	public ChatDto createChat(Long reqUser , Long user);
	
	public ChatDto findChatById(Long chatId);
	
	public List<ChatDto> findUsersChat(Long userId);
}
