package com.sm.SocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.SocialMedia.entity.PostComment;

public interface commentRepository extends JpaRepository<PostComment, Long>{

}
