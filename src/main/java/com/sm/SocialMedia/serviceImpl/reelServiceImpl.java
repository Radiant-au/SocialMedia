package com.sm.SocialMedia.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.SocialMedia.dto.ReelDto;
import com.sm.SocialMedia.entity.Reels;
import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.mapper.ReelsMapper;
import com.sm.SocialMedia.repository.ReelRepository;
import com.sm.SocialMedia.repository.userRepository;
import com.sm.SocialMedia.service.ReelService;

@Service
public class reelServiceImpl implements ReelService{
	
	@Autowired
	private ReelRepository reelRepo;
	
	@Autowired
	private userRepository userRepo;

	@Override
	public ReelDto createReel(Long userId, ReelDto rdto) {
		Users user = userRepo.findById(userId).orElseThrow(()-> new IllegalStateException("There is no user with " + userId));
		
		Reels createReel = new Reels();
		createReel.setTitle(rdto.getTitle());
		createReel.setVideo(rdto.getVideo());
		createReel.setUser(user);
		reelRepo.save(createReel);
		return ReelsMapper.mapToReelDto(createReel);
	}

	@Override
	public List<ReelDto> findAllReel() {
		return reelRepo.findAll().stream().map(ReelsMapper::mapToReelDto).collect(Collectors.toList());
	}

	@Override
	public List<ReelDto> findReelById(Long userId) {
		
		return reelRepo.findReelByUserId(userId).stream().map(ReelsMapper::mapToReelDto).collect(Collectors.toList());
	}

}
