package com.sm.SocialMedia.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sm.SocialMedia.Response.AuthResponse;
import com.sm.SocialMedia.config.jwtGenerator;
import com.sm.SocialMedia.dto.LoginDto;
import com.sm.SocialMedia.dto.UsersRegisterDto;
import com.sm.SocialMedia.entity.Users;
import com.sm.SocialMedia.mapper.usersMapper;
import com.sm.SocialMedia.repository.userRepository;
import com.sm.SocialMedia.service.AuthService;

@Service
public class authServiceImpl implements AuthService{
	
	@Autowired
    private userRepository userRepo;
	@Autowired
	private PasswordEncoder pencoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthResponse registerUser(UsersRegisterDto rudto) {
	    Optional<Users> existUser = userRepo.findByEmail(rudto.getEmail());
	    
	    if(existUser.isPresent()) {
	    	throw new IllegalStateException("There is user!!");
	    }
		Users user = usersMapper.mapToUser(rudto);
		user.setPassword(pencoder.encode(rudto.getPassword()));
		userRepo.save(user);
		 try {
		        Authentication authentication = authenticationManager.authenticate(
		            new UsernamePasswordAuthenticationToken(rudto.getEmail(), rudto.getPassword())
		        );

		        // Generate JWT token after successful authentication
		        String token = jwtGenerator.generateToken(authentication);

		        // Return the token in the response
		        return new AuthResponse(token, "Registration and login successful");

		    } catch (Exception ex) {
		        throw new IllegalStateException("Failed to auto-login after registration", ex);
		    }
	}

	@Override
	public AuthResponse LoginUser(LoginDto ldto) {
		try {
	        // Authenticate the user using email and password
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(ldto.getEmail(), ldto.getPassword())
	        );

	        // If authentication is successful, generate the JWT token
	        String token = jwtGenerator.generateToken(authentication);
	        return new AuthResponse(token, "Login Success");

	    } catch (BadCredentialsException e) {
	        throw new IllegalStateException("Invalid email or password");
	    }
	}

}
