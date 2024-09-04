package com.sm.SocialMedia.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class jwtGenerator {
	private static final String secret = "qweeakvkxhuskaliskjikll;oahdnvki";
	 private static final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
	
    // Static method to generate the token
    public static String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuer("aks") // Setting issuer
                .setIssuedAt(new Date()) // Setting issue date
                .setExpiration(new Date(new Date().getTime() + 86400000)) // Setting expiration (24 hours)
                .claim("email", auth.getName()) // Adding claim for user email
                .signWith(key) // Signing the JWT with the SecretKey
                .compact(); // Generating the compact form of JWT
        
        return jwt;
    }
    
    
    public static String getEmailfromToken(String jwt) {
    	jwt = jwt.substring(7);
    	Claims claims = Jwts.parserBuilder()
    					.setSigningKey(key).build().parseClaimsJws(jwt).getBody();
    	
    	String email = String.valueOf(claims.get("email"));
    	return email;
    }
    
    public static boolean validateToken(String token) {
    	token = token.substring(7);
    	try {
    		Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    		return true;
    	}catch (ExpiredJwtException ex) {
            // Handle the case when the token is expired
            throw new AuthenticationCredentialsNotFoundException("JWT is expired", ex);
        } catch (SignatureException ex) {
            // Handle the case when the token's signature is invalid
            throw new AuthenticationCredentialsNotFoundException("JWT signature is invalid", ex);
        } catch (MalformedJwtException ex) {
            // Handle the case when the token is malformed
            throw new AuthenticationCredentialsNotFoundException("JWT is malformed", ex);
        } catch (Exception ex) {
            // Handle any other exceptions that may occur during token parsing
            throw new AuthenticationCredentialsNotFoundException("JWT is invalid or incorrect", ex);
        }
    }
    
}
