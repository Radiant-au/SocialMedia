package com.sm.SocialMedia.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.SocialMedia.dto.ChatDto;
import com.sm.SocialMedia.entity.Chat;
import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.mapper.chatMapper;
import com.sm.SocialMedia.repository.ChatRepository;
import com.sm.SocialMedia.repository.userRepository;
import com.sm.SocialMedia.service.Chatservice;

@Service
public class chatServiceImpl implements Chatservice{
	
	@Autowired
	private ChatRepository chatRepo;
	
	@Autowired
	private userRepository userRepo;

	@Override
	public ChatDto createChat(Long reqUserId, Long user2Id) {
		Users reqUser = userRepo.findById(reqUserId).orElseThrow(() -> new IllegalStateException("There is no user with id " + reqUserId));
		Users user2 = userRepo.findById(user2Id).orElseThrow(() -> new IllegalStateException("There is no user with id " + user2Id));
		
		Chat isExist = chatRepo.findChatByUsersId(user2, reqUser);
		if(isExist != null) {
			return chatMapper.mapTochatDto(isExist);
		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chatRepo.save(chat);
		
		
		return chatMapper.mapTochatDto(chat);
	}

	@Override
	public ChatDto findChatById(Long chatId) {
		Chat chat = chatRepo.findById(chatId).orElseThrow(()-> new IllegalStateException("There is no chat with id " + chatId));
		return chatMapper.mapTochatDto(chat);
	}

	@Override
	public List<ChatDto> findUsersChat(Long userId) {
		// TODO Auto-generated method stub
		List<Chat> chat = chatRepo.findByUsersId(userId);
		
		return chat.stream().map(chatMapper::mapTochatDto).collect(Collectors.toList());
	}
	
	
}
