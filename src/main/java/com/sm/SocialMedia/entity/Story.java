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
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String caption;
	
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	
	@CreationTimestamp
	LocalDateTime createdAt;

	public Story(Long id, String caption, String image, Users user) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.user = user;
	}
}
