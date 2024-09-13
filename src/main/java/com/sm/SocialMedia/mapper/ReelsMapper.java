package com.sm.SocialMedia.mapper;

import com.sm.SocialMedia.dto.ReelDto;
import com.sm.SocialMedia.entity.Reels;
import com.sm.SocialMedia.entity.Users;

public class ReelsMapper {
	public static ReelDto mapToReelDto(Reels reel) {
		return new ReelDto(
					reel.getId(),
					reel.getTitle(),
					reel.getVideo(),
					reel.getUser().getId()
				);
	}
	
	public static Reels mapToReel(ReelDto reeldto , Users user) {
		return new Reels(
				reeldto.getId(),
				reeldto.getTitle(),
				reeldto.getVideo(),
				user
				);
	}
}
