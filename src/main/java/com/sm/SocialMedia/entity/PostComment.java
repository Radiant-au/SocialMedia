package com.sm.SocialMedia.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	    @JoinColumn(name = "post_id")
	    Post post;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    Users user;

	    @CreationTimestamp
	    LocalDateTime createdAt;

		public PostComment(Long id, String text, Post post, Users user) {
			super();
			this.id = id;
			this.text = text;
			this.post = post;
			this.user = user;
		}
}
