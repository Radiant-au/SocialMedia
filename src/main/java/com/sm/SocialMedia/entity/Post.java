package com.sm.SocialMedia.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String caption;
	String mediaUrl;

	@ManyToOne
	@JoinColumn(name = "user_id")
	Users user;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "post_likes", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<Users> liked = new HashSet<>();
	
	@OneToMany(mappedBy = "post" , fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    List<PostComment> comments = new ArrayList<>();

	@CreationTimestamp
	LocalDateTime createdAt;

	public Post(Long id, String caption, String mediaUrl , Users user, Set<Users> liked , List<PostComment> comments) {
		this.id = id;
		this.caption = caption;
		this.mediaUrl = mediaUrl;
		this.user = user;
		this.liked = liked;
		this.comments = comments;
	}

}
