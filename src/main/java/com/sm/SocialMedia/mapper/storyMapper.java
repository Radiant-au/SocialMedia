package com.sm.SocialMedia.mapper;

import com.sm.SocialMedia.dto.StoryDto;
import com.sm.SocialMedia.entity.Story;
import com.sm.SocialMedia.entity.Users;

public class storyMapper {
	public static StoryDto mapToStoryDto(Story story) {
		return new StoryDto(
					story.getId(),
					story.getCaption(),
					story.getImage(),
					story.getUser().getId(),
					story.getCreatedAt()
				);
	}
	
	public static Story mapToStory(StoryDto storyDto , Users user) {
		return new Story(
				storyDto.getId(),
				storyDto.getCaption(),
				storyDto.getImage(),
				user
				);
	}
}
