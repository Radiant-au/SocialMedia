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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.Response.ApiResponse;
import com.sm.SocialMedia.dto.PostDto;
import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.service.postService;
import com.sm.SocialMedia.service.userService;

@RestController
@RequestMapping("api/posts")
public class PostController {
	
	@Autowired
	postService pService;
	
	@Autowired
	userService uService;
	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto post ,@RequestHeader("Authorization") String jwt){
		UsersDto udto = uService.getUserfromToken(jwt);
		PostDto createPost = pService.createNewPost(post, udto.getId());
		return new ResponseEntity<>(createPost , HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId , @RequestHeader("Authorization") String jwt){
		UsersDto udto = uService.getUserfromToken(jwt);
		String message = pService.deletePost(postId,udto.getId());
		ApiResponse res = new ApiResponse(message , true);
		return new ResponseEntity<>(res , HttpStatus.OK);
	}
	
	@GetMapping("{postId}")
	public ResponseEntity<PostDto> findPostById(@PathVariable Long postId){
		PostDto post = pService.findPostById(postId);
		
		return new ResponseEntity<>(post , HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<PostDto>> findUsersPost(@RequestHeader("Authorization") String jwt){
		UsersDto udto = uService.getUserfromToken(jwt);
		List<PostDto> userPosts= pService.findPostByUserId(udto.getId());
		return new ResponseEntity<>(userPosts , HttpStatus.OK); 
	}
	
	@GetMapping("all")
	public ResponseEntity<List<PostDto>> findAllPost(){
		List<PostDto> posts = pService.findAllPost();
		return new ResponseEntity<>(posts , HttpStatus.OK);
	}
	
	@PutMapping("/save/{postId}")
	public ResponseEntity<PostDto> savedPostHandler(@PathVariable Long postId , @RequestHeader("Authorization") String jwt){
		UsersDto udto = uService.getUserfromToken(jwt);
		PostDto post = pService.savedPost(postId, udto.getId());
		
		return new ResponseEntity<>(post , HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/like/{postId}")
	public ResponseEntity<PostDto> LikePostHandler(@PathVariable Long postId , @RequestHeader("Authorization") String jwt){
		UsersDto udto = uService.getUserfromToken(jwt);
		PostDto post = pService.likePost(postId, udto.getId());
		
		return new ResponseEntity<>(post , HttpStatus.ACCEPTED);
	}
	

}
