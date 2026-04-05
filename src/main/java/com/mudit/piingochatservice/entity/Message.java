package com.mudit.piingochatservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "conversation_id")
    private UUID conversationId;

    @Column(name = "sender_id")
    private UUID senderId;

    @Column(name = "content")
    private String content;

    @Column(name = "sent_at", updatable = false)
    private OffsetDateTime sentAt;

    @Column(name = "is_read")
    private boolean isRead;

    @PrePersist
    protected void onCreate() {
        sentAt = OffsetDateTime.now();
    }
}
