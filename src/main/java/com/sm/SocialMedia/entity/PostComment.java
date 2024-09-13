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
public class PostComment {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    Long id;

	    String text;

	    @ManyToOne
	    @JoinColumn(name = "post_id" , nullable = false)
	    Post post;

	    @ManyToOne
	    @JoinColumn(name = "user_id" , nullable = false)
	    Users user;
	    
	    @ManyToOne
	    @JoinColumn(name = "parent_comment_id", nullable = true)
	    private PostComment parentComment;
	    
	    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	    private List<PostComment> replies = new ArrayList<>();
	    
	    @ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "comment_likes", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
		Set<Users> commentLikes = new HashSet<>();

	    @CreationTimestamp
	    LocalDateTime createdAt;

		public PostComment(Long id, String text, Post post, Users user ,  Set<Users> commentLikes) {
			this.id = id;
			this.text = text;
			this.post = post;
			this.user = user;
			this.commentLikes = commentLikes;
		}
}
