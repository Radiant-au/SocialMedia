package com.sm.SocialMedia.serviceImpl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sm.SocialMedia.config.jwtGenerator;
import com.sm.SocialMedia.dto.UsersDto;
import com.sm.SocialMedia.dto.UsersRegisterDto;
import com.sm.SocialMedia.dto.userUpdateDto;
import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.mapper.usersMapper;
import com.sm.SocialMedia.repository.userRepository;
import com.sm.SocialMedia.service.userService;

@Service
public class usersImpl implements userService{
	
	private userRepository userRepo;
	

	public usersImpl(userRepository userRepo) {
		this.userRepo = userRepo;
	}
	


	@Override
	public UsersDto getUserbyId(Long id) {
		Users user = userRepo.findById(id).orElseThrow(() -> new IllegalStateException("User does not exist wiht Id = " + id));
		return usersMapper.mapToUserDto(user);
	}

	@Override
	public UsersDto findByEmail(String email) {
		Users user = userRepo.findByEmail(email).orElseThrow(()-> new IllegalStateException("User does not exist with email = "+ email));
		return usersMapper.mapToUserDto(user);
	}

	@Override
	public UsersDto followUser(Long id1, Long id2) {
		Users user1 = userRepo.findById(id1).orElseThrow(() -> new IllegalStateException("User does not exist wiht Id = " + id1));
		Users user2 = userRepo.findById(id2).orElseThrow(() -> new IllegalStateException("User does not exist wiht Id = " + id2));
		user1.getFollowings().add(user2);
		user2.getFollowers().add(user1);
		userRepo.save(user1);
		userRepo.save(user2);
		return usersMapper.mapToUserDto(user1);
	}

	@Override
	public UsersDto UpdateUser(Long id,userUpdateDto udto) {
		Users user = userRepo.findById(id).orElseThrow(() -> new IllegalStateException("User does not exist wiht Id = " + id));
		Optional.ofNullable(udto.getUsername()).ifPresent(user::setUsername);
		Optional.ofNullable(udto.getProfileImg()).ifPresent(user::setProfileImg);
	
		
		Users updated_user = userRepo.save(user);
		
		return usersMapper.mapToUserDto(updated_user);
	}

	@Override
	public List<UsersDto> searchUser(String query) {
		return userRepo.searchUser(query).stream().map(usersMapper::mapToUserDto).collect(Collectors.toList());
	}


	@Override
	public List<UsersDto> getAllUser() {
		return userRepo.findAll().stream().map(usersMapper::mapToUserDto).collect(Collectors.toList());
	}



	@Override
	public UsersDto getUserfromToken(String token) {
		String email = jwtGenerator.getEmailfromToken(token);
		Users user = userRepo.findByEmail(email).orElseThrow(() -> new IllegalStateException("There is no user with email =" + email));
		return usersMapper.mapToUserDto(user);
	}

}
