package com.sm.SocialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.SocialMedia.entity.Message;

public interface MessageRepository extends JpaRepository<Message , Long>{

	public List<Message> findByChatId(Long chatId);
}
