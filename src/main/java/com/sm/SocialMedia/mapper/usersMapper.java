package com.sm.SocialMedia.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.dto.UsersDto2;
import com.sm.SocialMedia.dto.UsersRegisterDto;
import com.sm.SocialMedia.dto.userUpdateDto;
import com.sm.SocialMedia.entity.Users;

public class usersMapper {
	public static UsersDto mapToUserDto(Users user) {
		return new UsersDto(
	            user.getId(),
	            user.getUsername(),
	            user.getProfileImg(),
	            user.getEmail(),
	            user.getGender(),
	            user.getFollowers() != null ? 
	                user.getFollowers().stream().map(Users::getId).collect(Collectors.toSet()) : new HashSet<>(),
	            user.getFollowings() != null ? 
	                user.getFollowings().stream().map(Users::getId).collect(Collectors.toSet()) : new HashSet<>(),
	            user.getPosts()!= null ?
	            	user.getPosts().stream().map(postMapper::mapToPostDto).collect(Collectors.toList()) : new ArrayList<>(),
	            user.getSavedPost() != null ? 
	                user.getSavedPost().stream().map(postMapper::mapToPostDto).collect(Collectors.toSet()) : new HashSet<>(),
	            user.getRoles() != null ?
	            		user.getRoles().stream().collect(Collectors.toSet()) : new HashSet<>()
	        );
	}
	
	public static UsersDto2 mapToUserDto2(Users user) {
		return new UsersDto2(
					user.getId(),
					user.getUsername(),
					user.getEmail(),
					user.getGender()
				);
	}

	public static Users mapToUser(UsersRegisterDto RuserDto) {
		return new Users(RuserDto.getUsername(), RuserDto.getEmail(), RuserDto.getPassword() , RuserDto.getGender());
	}
	public static Users mapToUser(userUpdateDto ud) {
		return new Users(ud.getUsername(), ud.getProfileImg());
	}
}
