package com.sm.SocialMedia.dto;

import java.util.List;
import java.util.Set;

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
	    private String username;
	    private Set<Long> likedUserIds;
	    private List<Long> replyId;
  
}
