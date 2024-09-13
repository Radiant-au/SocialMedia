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
public class StoryDto {
	private long id;
	private String caption;
	private String image;
	private long userId;
	private LocalDateTime createdAt;
}
