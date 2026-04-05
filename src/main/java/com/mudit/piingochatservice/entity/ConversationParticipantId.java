package com.mudit.piingochatservice.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class ConversationParticipantId implements Serializable {
    private UUID conversationId;
    private UUID userId;
}
