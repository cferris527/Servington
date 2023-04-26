package com.example.experiment1.BasicChat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long>{

}

