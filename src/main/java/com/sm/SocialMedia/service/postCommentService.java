package com.sm.SocialMedia.service;

import com.sm.SocialMedia.dto.PostCommentDto;

public interface PostCommentService {
	
	public PostCommentDto addComment(Long userId , Long postId , PostCommentDto pdto);
	
	public String deleteComment(Long userId , Long commentId);
}
