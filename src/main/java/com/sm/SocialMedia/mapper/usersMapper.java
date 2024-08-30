package com.sm.SocialMedia.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.entity.Users;

public class usersMapper {
	public static UsersDto mapToUserDto(Users user) {
		return new UsersDto(
	            user.getId(),
	            user.getUsername(),
	            user.getEmail(),
	            user.getGender(),
	            user.getFollowers() != null ? 
	                user.getFollowers().stream().map(Users::getId).collect(Collectors.toSet()) : new HashSet<>(),
	            user.getFollowings() != null ? 
	                user.getFollowings().stream().map(Users::getId).collect(Collectors.toSet()) : new HashSet<>(),
	            user.getPosts()!= null ?
	            	user.getPosts().stream().map(postMapper::mapToPostDto).collect(Collectors.toList()) : new ArrayList<>(),
	            user.getSavedPost() != null ? 
	                user.getSavedPost().stream().map(postMapper::mapToPostDto).collect(Collectors.toSet()) : new HashSet<>()
	        );
	}

	public static Users mapToUser(UsersDto userDto) {
		return new Users(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getGender(),
				new HashSet<>(), // Followers and Followings will be handled separately
				new HashSet<>(),
				new ArrayList<>(),
				new HashSet<>());
	}
}
