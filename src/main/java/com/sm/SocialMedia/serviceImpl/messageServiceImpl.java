package com.sm.SocialMedia.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.SocialMedia.dto.MessageDto;
import com.sm.SocialMedia.entity.Chat;
import com.sm.SocialMedia.entity.Message;
import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.mapper.messageMapper;
import com.sm.SocialMedia.repository.ChatRepository;
import com.sm.SocialMedia.repository.MessageRepository;
import com.sm.SocialMedia.repository.userRepository;
import com.sm.SocialMedia.service.MessageService;

@Service
public class messageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository mRepo;
	
	@Autowired
	private ChatRepository cRepo;
	
	@Autowired
	private userRepository uRepo;

	@Override
	public MessageDto createMessage(Long userId, Long chatId, Message req) {
		
		Message message = new Message();
		Chat chat = cRepo.findById(chatId).orElseThrow(() -> new IllegalStateException("There is no chat with id " + chatId));
		Users user = uRepo.findById(userId).orElseThrow(() -> new IllegalStateException("There is no user with id " + userId));
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		
		Message saveMessage = mRepo.save(message);
		chat.getMessages().add(saveMessage);
		cRepo.save(chat);
		
		return messageMapper.mapToMessageDto(saveMessage);
		
	}

	@Override
	public List<MessageDto> findChatMessages(Long chatId) {
		return mRepo.findByChatId(chatId).stream().map(messageMapper::mapToMessageDto).collect(Collectors.toList());
	}
	
}
