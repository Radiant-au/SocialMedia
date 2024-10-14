package com.sm.SocialMedia.service;

import java.util.List;

import com.sm.SocialMedia.dto.MessageDto;
import com.sm.SocialMedia.entity.Message;

public interface MessageService {

	public MessageDto createMessage(Long userId , Long chatId , Message req);
	
	public List<MessageDto> findChatMessages(Long chatId);
}
