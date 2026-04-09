package com.mudit.piingochatservice.repository;

import com.mudit.piingochatservice.entity.ConversationParticipant;
import com.mudit.piingochatservice.entity.ConversationParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConversationParticipantRepository extends JpaRepository<ConversationParticipant, ConversationParticipantId> {
    List<ConversationParticipant> findByIdUserId(UUID userId);
}
