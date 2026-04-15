package com.mudit.piingochatservice.repository;

import com.mudit.piingochatservice.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    Page<Message> findByConversation_IdOrderBySentAtAsc(UUID conversationId, Pageable pageable);
}