package com.sm.SocialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.Response.ApiResponse;
import com.sm.SocialMedia.dto.PostCommentDto;
import com.sm.SocialMedia.dto.PostDto;
import com.sm.SocialMedia.service.postCommentService;
import com.sm.SocialMedia.service.postService;

@RestController
@RequestMapping("api/posts")
public class PostController {
	
	@Autowired
	postService pService;
	
	@Autowired
	postCommentService cService;
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto post ,@PathVariable Long userId){
		PostDto createPost = pService.createNewPost(post, userId);
		return new ResponseEntity<>(createPost , HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId , @PathVariable Long userId){
		String message = pService.deletePost(postId, userId);
		ApiResponse res = new ApiResponse(message , true);
		return new ResponseEntity<>(res , HttpStatus.OK);
	}
	
	@GetMapping("{postId}")
	public ResponseEntity<PostDto> findPostById(@PathVariable Long postId){
		PostDto post = pService.findPostById(postId);
		
		return new ResponseEntity<>(post , HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> findUsersPost(@PathVariable Long userId){
		List<PostDto> userPosts= pService.findPostByUserId(userId);
		return new ResponseEntity<>(userPosts , HttpStatus.OK); 
	}
	
	@GetMapping
	public ResponseEntity<List<PostDto>> findAllPost(){
		List<PostDto> posts = pService.findAllPost();
		return new ResponseEntity<>(posts , HttpStatus.OK);
	}
	
	@PutMapping("/save/{postId}/user/{userId}")
	public ResponseEntity<PostDto> savedPostHandler(@PathVariable Long postId , @PathVariable Long userId){
		PostDto post = pService.savedPost(postId, userId);
		
		return new ResponseEntity<>(post , HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/like/{postId}/user/{userId}")
	public ResponseEntity<PostDto> LikePostHandler(@PathVariable Long postId , @PathVariable Long userId){
		PostDto post = pService.likePost(postId, userId);
		
		return new ResponseEntity<>(post , HttpStatus.ACCEPTED);
	}
	
	@PostMapping("{postId}/comments/user/{userId}")
	public ResponseEntity<PostCommentDto> addComment(@PathVariable Long postId , @PathVariable Long userId , @RequestBody PostCommentDto cdto) {
		 PostCommentDto comment = cService.addComment(userId, postId, cdto);
		 
		 return new ResponseEntity<>(comment , HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/comments/{commentId}/user/{userId}")
	public ResponseEntity<String> deleteComment(@PathVariable Long commentId ,@PathVariable Long userId){
		String message = cService.deleteComment(userId, commentId);
		ApiResponse res = new ApiResponse(message , true);
		
		return new ResponseEntity<>(message , HttpStatus.OK);
	}
}
