package com.sm.SocialMedia.dto;

import java.util.List;
import java.util.Set;

import com.sm.SocialMedia.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersDto {
	  	private Long id;
	    private String username;
	    private String email;
	    private String gender;

	    // Follower and Following information
	    private Set<Long> followers;
	    private Set<Long> followings;
	    private List<PostDto> posts;
	    private Set<PostDto> savedPost;
}
