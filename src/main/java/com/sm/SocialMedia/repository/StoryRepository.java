package com.sm.SocialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sm.SocialMedia.entity.Story;

public interface StoryRepository extends JpaRepository<Story, Long>{

	@Query("select s from Story s where s.user.id=:userId")
	List<Story> findStoryByUserId(Long userId);
}
