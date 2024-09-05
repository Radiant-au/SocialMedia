package com.sm.SocialMedia.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.SocialMedia.dto.PostDto;
import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.entity.Post;
import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.mapper.postMapper;
import com.sm.SocialMedia.repository.postRepostitory;
import com.sm.SocialMedia.repository.userRepository;
import com.sm.SocialMedia.service.postService;
import com.sm.SocialMedia.service.userService;

import jakarta.transaction.Transactional;

@Service
public class postImpl implements postService{
	
	@Autowired
	postRepostitory postRepo;
	
	@Autowired
	userService uService;
	
	@Autowired
	userRepository userRepo;

	@Override
	public PostDto createNewPost(PostDto postdto, Long userId) {
		
		Users user = userRepo.findById(userId).orElseThrow(()-> new IllegalStateException("There is no user with " + userId));
		Post newPost = new Post();
		newPost.setCaption(postdto.getCaption());
		newPost.setMediaUrl(postdto.getMediaUrl());
		newPost.setUser(user);
		postRepo.save(newPost);
		return postMapper.mapToPostDto(newPost);
	}

	@Transactional
	@Override
	public String deletePost(Long postId, Long userId){
		Post post = postRepo.findById(postId).orElseThrow(()-> new IllegalStateException("There is no post with " + postId));
		UsersDto user = uService.getUserbyId(userId);
		
		if(post.getUser().getId() != user.getId()) {
			throw new IllegalArgumentException("You can't delete another user's post");
		}
		// Clear associations
		userRepo.deleteSavedPostsByPostId(postId);
	    post.getLiked().clear();
	    post.getComments().clear();
	    post.getUser().getPosts().remove(post);
	    
	    postRepo.deleteById(postId);
	    return "post deleted successfully";
	}

	@Override
	public List<PostDto> findPostByUserId(Long id) {
		return postRepo.findPostByUserId(id).stream().map(postMapper::mapToPostDto).collect(Collectors.toList());
	}

	@Override
	public PostDto findPostById(Long postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new IllegalStateException("There is no post with " + postId));
		return postMapper.mapToPostDto(post);
	}

	@Override
	public List<PostDto> findAllPost() {
		// TODO Auto-generated method stub
		return postRepo.findAll().stream().map(postMapper::mapToPostDto).collect(Collectors.toList());
	}

	@Override
	public PostDto savedPost(Long postId, Long userId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new IllegalStateException("There is no post with " + postId));
		Users user = userRepo.findById(userId).orElseThrow(() -> new IllegalStateException("There is no user with" + userId));
		
		if (post.getUser().getId().equals(userId)) {
	        throw new IllegalStateException("You cannot save your own post.");
	    }
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		userRepo.save(user);
		
		return postMapper.mapToPostDto(post);
	}

	@Override
	public PostDto likePost(Long postId, Long userId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new IllegalStateException("There is no post with " + postId));
		Users user = userRepo.findById(userId).orElseThrow(() -> new IllegalStateException("No user found with ID " + userId));
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);
		}
		
		Post updatedPost = postRepo.save(post);
		return postMapper.mapToPostDto(updatedPost);
	}

}
