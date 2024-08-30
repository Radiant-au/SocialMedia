package com.sm.SocialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sm.SocialMedia.entity.Post;

public interface postRepostitory extends JpaRepository<Post, Long>{

	@Query("select p from Post p where p.user.id=:userId")
	List<Post> findPostByUserId(Long userId);
}
