package com.sm.SocialMedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.SocialMedia.Response.ApiResponse;
import com.sm.SocialMedia.dto.PostCommentDto;
import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.service.PostCommentService;
import com.sm.SocialMedia.service.postService;
import com.sm.SocialMedia.service.userService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	postService pService;
	
	@Autowired
	PostCommentService cService;
	
	@Autowired
	userService uService;
	
	@PostMapping("{postId}")
	public ResponseEntity<PostCommentDto> addComment(@PathVariable Long postId , @RequestHeader("Authorization") String jwt , @RequestBody PostCommentDto cdto) {
		UsersDto udto = uService.getUserfromToken(jwt);
		 PostCommentDto comment = cService.addComment(udto.getId(), postId, cdto);
		 
		 return new ResponseEntity<>(comment , HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId ,@RequestHeader("Authorization") String jwt){
		UsersDto udto = uService.getUserfromToken(jwt);
		String message = cService.deleteComment(udto.getId(), commentId);
		ApiResponse res = new ApiResponse(message , true);
		
		return new ResponseEntity<>(res , HttpStatus.OK);
	}
	
	@PutMapping("/like/{commentId}")
	public ResponseEntity<PostCommentDto> LikePostHandler(@PathVariable Long commentId , @RequestHeader("Authorization") String jwt){
		UsersDto udto = uService.getUserfromToken(jwt);
		PostCommentDto comment = cService.likeComment(commentId, udto.getId());
		
		return new ResponseEntity<>(comment , HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/reply/{commentId}")
	public ResponseEntity<PostCommentDto> replyHandler(@PathVariable Long commentId , @RequestHeader("Authorization") String jwt , @RequestBody PostCommentDto cdto){
		UsersDto udto = uService.getUserfromToken(jwt);
		PostCommentDto comment = cService.replyComment(udto.getId(), commentId, cdto);
		
		return new ResponseEntity<>(comment , HttpStatus.ACCEPTED);
	}
}
