package com.sm.SocialMedia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sm.SocialMedia.entity.Users;

import jakarta.transaction.Transactional;

public interface userRepository extends JpaRepository<Users, Long>{
	
	Optional<Users> findByEmail(String email);
	
	@Query("select u from Users u where u.username like %:query% or u.email like %:query%")
	public List<Users> searchUser(@Param("query") String query);
	
	 @Modifying
	 @Transactional
	 @Query(value = "DELETE FROM user_saved_posts WHERE post_id = :postId", nativeQuery = true)
	 void deleteSavedPostsByPostId(@Param("postId") Long postId);
}
