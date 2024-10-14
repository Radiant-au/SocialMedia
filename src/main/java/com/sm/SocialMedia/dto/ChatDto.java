package com.sm.SocialMedia.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatDto {
	private Long id;
	private String chat_name;
	private String chat_image;
	private List<UsersDto2> users;
	private LocalDateTime createdAt;
}
