package com.sm.SocialMedia.mapper;

import com.sm.SocialMedia.dto.PostCommentDto;
import com.sm.SocialMedia.entity.Post;
import com.sm.SocialMedia.entity.PostComment;
import com.sm.SocialMedia.entity.Users;

public class postCommentMapper {
	// Map PostComment entity to PostCommentDto
    public static PostCommentDto mapToPostCommentDto(PostComment comment) {
        return new PostCommentDto(
            comment.getId(),
            comment.getText(),
            comment.getPost().getId(),
            comment.getUser().getId()  // Map user id
        );
    }

    // Map PostCommentDto to PostComment entity
    public static PostComment mapToPostComment(PostCommentDto commentDto, Post post, Users user) {
        return new PostComment(
            commentDto.getId(),
            commentDto.getText(),
            post,  // Assign post from Post entity
            user   // Assign user from Users entity
        );
    }
}
