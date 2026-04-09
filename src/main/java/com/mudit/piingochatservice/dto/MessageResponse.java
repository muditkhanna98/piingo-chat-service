package com.mudit.piingochatservice.dto;

import com.mudit.piingochatservice.entity.Message;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MessageResponse(UUID id, UUID conversationId, UUID senderId, String content, OffsetDateTime sentAt, boolean isRead) {

    public static MessageResponse from(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getConversationId(),
                message.getSenderId(),
                message.getContent(),
                message.getSentAt(),
                message.isRead()
        );
    }
}