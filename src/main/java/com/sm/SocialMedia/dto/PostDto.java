package com.sm.SocialMedia.dto;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
	 Long id;
	 String caption;
	 String mediaUrl;
	 Long userId;
	 Set<Long> likedUserIds;
	 List<PostCommentDto> comments;
	
	
}
