package com.sm.SocialMedia.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.SocialMedia.dto.StoryDto;
import com.sm.SocialMedia.entity.Story;
import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.mapper.storyMapper;
import com.sm.SocialMedia.repository.StoryRepository;
import com.sm.SocialMedia.repository.userRepository;
import com.sm.SocialMedia.service.StoryService;

@Service
public class storyServiceImpl implements StoryService{
	
	@Autowired
	StoryRepository sRepo;
	
	@Autowired
	userRepository uRepo;

	@Override
	public StoryDto createStory(Long userId, StoryDto sdto) {
		
		Users user = uRepo.findById(userId).orElseThrow(()-> new IllegalStateException("There is no user with id " + userId));
		
		
		Story newStory = new Story();
		newStory.setCaption(sdto.getCaption());
		newStory.setImage(sdto.getImage());
		newStory.setUser(user);
		
		sRepo.save(newStory);
		
		return storyMapper.mapToStoryDto(newStory);
	}

	@Override
	public List<StoryDto> getStoryByuserId(Long userId) {
		return sRepo.findStoryByUserId(userId).stream().map(storyMapper::mapToStoryDto).collect(Collectors.toList());
	}

}
