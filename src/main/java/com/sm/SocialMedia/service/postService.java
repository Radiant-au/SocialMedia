package com.sm.SocialMedia.service;

import java.util.List;

import com.sm.SocialMedia.dto.PostDto;


public interface postService {
	
	public PostDto createNewPost(PostDto postdto , Long userId);
	
	public String deletePost(Long postId, Long userId);
	
	List<PostDto> findPostByUserId(Long id);
	
	PostDto findPostById(Long postId);
	
	List<PostDto> findAllPost();
	
	PostDto savedPost(Long postId , Long userId);
	
	PostDto likePost(Long postId , Long userId);
	
}
