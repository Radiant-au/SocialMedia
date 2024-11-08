package com.sm.SocialMedia.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.repository.userRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private userRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Users user =  userRepo.findByEmail(email).orElseThrow(()-> new IllegalStateException("user does not exist with " + email));
		List<GrantedAuthority> authorities = user.getRoles().stream()
		        .map(role -> new SimpleGrantedAuthority(role.getName()))
		        .collect(Collectors.toList());
		return new User(user.getEmail(), user.getPassword(), authorities);
	}

}
