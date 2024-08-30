package com.sm.SocialMedia.service;

import com.sm.SocialMedia.dto.PostCommentDto;

public interface postCommentService {
	
	public PostCommentDto addComment(Long userId , Long postId , PostCommentDto pdto);
	
	public String deleteComment(Long userId , Long commentId);
}
