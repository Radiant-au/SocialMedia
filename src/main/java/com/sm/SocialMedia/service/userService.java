package com.sm.SocialMedia.service;

import java.util.List;

import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.dto.UsersRegisterDto;
import com.sm.SocialMedia.dto.userUpdateDto;

public interface userService {
	
	public List<UsersDto> getAllUser();
	
	public UsersDto getUserbyId(Long id);
	
	public UsersDto findByEmail(String email);
	
	public UsersDto followUser(Long id1 , Long id2);
	
	public UsersDto UpdateUser(Long id ,userUpdateDto udto);
	
	public List<UsersDto> searchUser(String query);
	
	public UsersDto getUserfromToken(String token);
}
