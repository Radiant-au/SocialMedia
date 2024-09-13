package com.sm.SocialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sm.SocialMedia.entity.Reels;

public interface ReelRepository extends JpaRepository<Reels, Long>{
	
	@Query("select r from Reels r where r.user.id=:userId")
	List<Reels> findReelByUserId(Long userId);
}
