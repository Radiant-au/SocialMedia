package com.sm.SocialMedia.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.SocialMedia.dto.PostCommentDto;
import com.sm.SocialMedia.entity.Post;
import com.sm.SocialMedia.entity.PostComment;
import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.mapper.postCommentMapper;
import com.sm.SocialMedia.repository.commentRepository;
import com.sm.SocialMedia.repository.postRepostitory;
import com.sm.SocialMedia.repository.userRepository;
import com.sm.SocialMedia.service.PostCommentService;

import jakarta.transaction.Transactional;

@Service
public class commentImpl implements PostCommentService{
	
	 @Autowired
	 private commentRepository postCommentRepo;

	 @Autowired
	 private postRepostitory postRepo;

	 @Autowired
	 private userRepository userRepo;

	@Transactional
	@Override
	public PostCommentDto addComment(Long userId, Long postId, PostCommentDto pdto) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new IllegalStateException("There is no post"));
		Users user = userRepo.findById(userId).orElseThrow(() -> new IllegalStateException("There is no user"));
		
		PostComment comment = new PostComment();
		 comment.setPost(post);
	     comment.setUser(user);
	     comment.setText(pdto.getText());
	     postCommentRepo.save(comment);

	     return postCommentMapper.mapToPostCommentDto(comment);
	}

	@Transactional
	@Override
	public String deleteComment(Long userId, Long commentId) {
		PostComment comment = postCommentRepo.findById(commentId).orElseThrow(() -> new IllegalStateException("Comment not found"));
	        
	    if (!comment.getUser().getId().equals(userId)) {
	         throw new IllegalArgumentException("You can't delete another user's comment");
	     }

	    postCommentRepo.delete(comment);
	    return "Comment deleted successfully";
	}

	@Override
	public PostCommentDto likeComment(Long commentId, Long userId) {
		PostComment comment = postCommentRepo.findById(commentId).orElseThrow(()-> new IllegalStateException("There is no post with " + commentId));
		Users user = userRepo.findById(userId).orElseThrow(() -> new IllegalStateException("No user found with ID " + userId));
		
		if(comment.getCommentLikes().contains(user)) {
			comment.getCommentLikes().remove(user);
		}else {
			comment.getCommentLikes().add(user);
		}
		
		PostComment updatedComment = postCommentRepo.save(comment);
		return postCommentMapper.mapToPostCommentDto(updatedComment);
	}

	@Override
	public PostCommentDto replyComment(Long userId, Long commentId, PostCommentDto pdto) {
		 	Users user = userRepo.findById(userId).orElseThrow(() -> new IllegalStateException("There is no user"));
	        PostComment parentComment = postCommentRepo.findById(commentId).orElseThrow(() -> new IllegalStateException("There is no comment"));

	        // Create the reply comment entity
	        PostComment replyComment = new PostComment();
	        replyComment.setText(pdto.getText());
	        replyComment.setUser(user);
	        replyComment.setPost(parentComment.getPost());
	        replyComment.setParentComment(parentComment); // Set the parent comment

	        // Save the reply comment
	        postCommentRepo.save(replyComment);

	        // Optionally, you can add this reply to the parent's replies list
	        parentComment.getReplies().add(replyComment);
	        postCommentRepo.save(parentComment);

	        return postCommentMapper.mapToPostCommentDto(replyComment);
	}

}
