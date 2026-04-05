package com.mudit.piingochatservice.controller;

import com.mudit.piingochatservice.dto.CreateConversationRequest;
import com.mudit.piingochatservice.service.ConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/conversations")
@Tag(name = "Conversations", description = "Conversation and messaging APIs")
@AllArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;

    @PostMapping
    @Operation(summary = "Create a conversation", description = "Creates a new conversation. The createdBy user is automatically added as a participant.")
    public ResponseEntity<UUID> createConversation(@RequestBody CreateConversationRequest createConversationRequest) {
        UUID conversationId = conversationService.createNewConversation(createConversationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(conversationId);
    }

}
