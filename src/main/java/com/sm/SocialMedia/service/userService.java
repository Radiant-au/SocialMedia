package com.sm.SocialMedia.service;

import java.util.List;

import com.sm.SocialMedia.dto.UsersDto;

public interface userService {
	
	public List<UsersDto> getAllUser();
	
	public UsersDto registerUser(UsersDto udto);
	
	public UsersDto getUserbyId(Long id);
	
	public UsersDto findByEmail(String email);
	
	public UsersDto followUser(Long id1 , Long id2);
	
	public UsersDto UpdateUser(Long id ,UsersDto udto);
	
	public List<UsersDto> searchUser(String query);
}
