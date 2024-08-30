package com.sm.SocialMedia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostCommentDto {
	 	private Long id;
	    private String text;
	    private Long postId;
	    private Long userId;
	    
}
