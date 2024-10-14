package com.sm.SocialMedia.mapper;

import com.sm.SocialMedia.dto.MessageDto;
import com.sm.SocialMedia.entity.Message;

public class messageMapper {
	public static MessageDto mapToMessageDto(Message message) {
		return new MessageDto(
				message.getId(),
				message.getContent(),
				message.getImage(),
				usersMapper.mapToUserDto2(message.getUser()),
				message.getCreatedAt()
				);
	}
}
