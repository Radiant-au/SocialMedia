package com.sm.SocialMedia.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sm.SocialMedia.entity.Chat;
import com.sm.SocialMedia.entity.Users;

public interface ChatRepository extends JpaRepository<Chat, Long>{
	
	public List<Chat> findByUsersId(Long userId);
	
	@Query("select c from Chat c where :user Member of c.users and :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") Users user , @Param("reqUser") Users reqUser);
}
