package com.sm.SocialMedia.service;

import java.util.List;

import com.sm.SocialMedia.dto.ReelDto;

public interface ReelService {
	public ReelDto createReel(Long userId , ReelDto rdto);
	
	public List<ReelDto> findAllReel();
	
	public List<ReelDto> findReelById(Long userId);
}
