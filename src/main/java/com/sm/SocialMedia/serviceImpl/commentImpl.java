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
import com.sm.SocialMedia.service.postCommentService;

import jakarta.transaction.Transactional;

@Service
public class commentImpl implements postCommentService{
	
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
		Users user = userRepo.findById(userId).orElseThrow(() -> new IllegalStateException("There is no post"));
		
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

}
