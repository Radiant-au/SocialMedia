package com.sm.SocialMedia.dto;

import java.util.List;
import java.util.Set;

import com.sm.SocialMedia.entity.Role;

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
	    // post and saved posts
	    private List<PostDto> posts;
	    private Set<PostDto> savedPost;
	    private Set<Role> role;
}
