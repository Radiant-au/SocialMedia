package com.sm.SocialMedia.service;

import java.util.List;

import com.sm.SocialMedia.dto.StoryDto;

public interface StoryService {
	
	public StoryDto createStory(Long userId , StoryDto sdto);
	
	public List<StoryDto> getStoryByuserId(Long userId);
}
