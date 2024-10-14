package com.sm.SocialMedia.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            comment.getPost().getId(),             // Post ID
            comment.getUser().getProfileImg(),
            comment.getUser().getUsername(),       // Username
            comment.getCommentLikes() != null ? 
                comment.getCommentLikes().stream()  // Map liked user IDs
                    .map(Users::getId)
                    .collect(Collectors.toSet()) : new HashSet<>(),
            mapRepliesToIds(comment.getReplies())   // Map replies to reply ID
        );
    }

    // Helper method to map replies to a list of reply IDs
    private static List<Long> mapRepliesToIds(List<PostComment> replies) {
        return replies != null ? 
            replies.stream()
                   .map(PostComment::getId)  // Extract only the reply IDs
                   .collect(Collectors.toList()) : new ArrayList<>();
    }

    // Map PostCommentDto to PostComment entity
    public static PostComment mapToPostComment(PostCommentDto commentDto, Post post, Users user, PostComment parentComment, Set<Users> likedUsers) {
        PostComment postComment = new PostComment(
            commentDto.getId(),
            commentDto.getText(),
            post,   // Assign post from Post entity
            user,   // Assign user from Users entity
            likedUsers != null ? likedUsers : new HashSet<>()
        );

        // Set the parent comment (if it's a reply, otherwise null)
        postComment.setParentComment(parentComment);

        return postComment;
    }
}
