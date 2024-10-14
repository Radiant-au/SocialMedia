package com.sm.SocialMedia.entity;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String profileImg;
	String username;
	String email;
	String password;
	String gender;
	
	 @ManyToMany(fetch = FetchType.LAZY)
	 @JoinTable(
	      name = "user_followings",
	      joinColumns = @JoinColumn(name = "follower_id"),
	      inverseJoinColumns = @JoinColumn(name = "followed_id")
	 )
	  Set<Users> followings = new HashSet<>();

	 
	  @ManyToMany(mappedBy = "followings" , fetch = FetchType.LAZY)
	  Set<Users> followers = new HashSet<>();
	  
	  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
	  private List<Post> posts = new ArrayList<>();
	  
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(
	        name = "user_saved_posts",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "post_id")
	   )
	   private Set<Post> savedPost = new HashSet<>();
	  
	  @ManyToMany(fetch = FetchType.EAGER)
	  @JoinTable(
	        name = "user_roles",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles = new HashSet<>();

	public Users( String username, String email, String password, String gender) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}
	  
	public Users( String username, String profileImg) {
		this.username = username;
		this.profileImg = profileImg;
	} 
	
}
