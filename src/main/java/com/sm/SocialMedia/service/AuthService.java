package com.sm.SocialMedia.service;

import com.sm.SocialMedia.Response.AuthResponse;
import com.sm.SocialMedia.dto.LoginDto;
import com.sm.SocialMedia.dto.UsersRegisterDto;

public interface AuthService {
	public AuthResponse registerUser(UsersRegisterDto rudto);
	
	public AuthResponse LoginUser(LoginDto ldto);
}
