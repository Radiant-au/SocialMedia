package com.sm.SocialMedia.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    Long id;
	String content;
	String image;
	UsersDto2 user;
	LocalDateTime createdAt;
	
}
