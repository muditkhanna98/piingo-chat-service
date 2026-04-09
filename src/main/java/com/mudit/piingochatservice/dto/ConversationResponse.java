package com.mudit.piingochatservice.dto;

import com.mudit.piingochatservice.entity.Conversation;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record ConversationResponse(UUID id, UUID createdBy, OffsetDateTime createdAt, List<UUID> participantIds,
                                   MessageResponse lastMessage) {

    public static ConversationResponse from(Conversation conversation) {
        return new ConversationResponse(
                conversation.getId(),
                conversation.getCreatedBy(),
                conversation.getCreatedAt(),
                List.of(),
                null
        );
    }
}
