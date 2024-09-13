package com.sm.SocialMedia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReelDto {
	private Long id;
	private String title;
	private String video;
	private Long userId;
}
