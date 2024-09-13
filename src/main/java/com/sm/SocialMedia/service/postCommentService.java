package com.sm.SocialMedia.service;

import com.sm.SocialMedia.dto.PostCommentDto;

public interface PostCommentService {
	
	public PostCommentDto addComment(Long userId , Long postId , PostCommentDto pdto);
	
	public PostCommentDto likeComment(Long commentId , Long userId);
	
	public String deleteComment(Long userId , Long commentId);
	
	public PostCommentDto replyComment(Long userId , Long commentId , PostCommentDto pdto);
}
