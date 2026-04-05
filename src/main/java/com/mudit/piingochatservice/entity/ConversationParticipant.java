package com.mudit.piingochatservice.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conversation_participants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationParticipant {
    @EmbeddedId
    private ConversationParticipantId id;
}
