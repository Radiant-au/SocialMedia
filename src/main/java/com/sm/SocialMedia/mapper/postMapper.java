package com.sm.SocialMedia.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.sm.SocialMedia.dto.PostDto;
import com.sm.SocialMedia.entity.Post;
import com.sm.SocialMedia.entity.Users;

public class postMapper {
	 // Map Post entity to PostDto with null checks
    public static PostDto mapToPostDto(Post post) {
        return new PostDto(
            post.getId(),
            post.getCaption(),
            post.getMediaUrl(),
            post.getUser().getId(),
            post.getLiked() != null ? 
            post.getLiked().stream()
                    .map(Users::getId)
                    .collect(Collectors.toSet()) : new HashSet<>(),
            post.getComments() != null ? 
                post.getComments().stream()
                    .map(postCommentMapper::mapToPostCommentDto)
                    .collect(Collectors.toList()) : new ArrayList<>()	
        );
    }

    // Map PostDto to Post entity with null checks
    public static Post mapToPost(PostDto postDto, Users user, Set<Users> likedUsers) {
        // Create the Post entity first, without comments
        Post post = new Post(
            postDto.getId(),
            postDto.getCaption(),
            postDto.getMediaUrl(),
            user,
            likedUsers != null ? likedUsers : new HashSet<>(),
            new ArrayList<>()
        );

        // Set the comments after Post has been created
        if (postDto.getComments() != null) {
            post.setComments(
                postDto.getComments().stream()
                    .map(commentDto -> postCommentMapper.mapToPostComment(commentDto, post , user)) // Now Post is available
                    .collect(Collectors.toList())
            );
        }

        return post;
    }
}
