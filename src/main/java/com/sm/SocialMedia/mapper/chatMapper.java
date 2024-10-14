package com.sm.SocialMedia.mapper;

import java.util.stream.Collectors;

import com.sm.SocialMedia.dto.ChatDto;
import com.sm.SocialMedia.entity.Chat;

public class chatMapper {
	public static ChatDto mapTochatDto(Chat chat) {
		return new ChatDto(
				 chat.getId(),
				 chat.getChat_name(),
				 chat.getChat_image(),
				 chat.getUsers().stream().map(usersMapper::mapToUserDto2).collect(Collectors.toList()),
				 chat.getCreatedAt()
				);
				
	}
}
