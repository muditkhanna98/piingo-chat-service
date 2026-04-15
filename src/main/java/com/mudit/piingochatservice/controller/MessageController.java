package com.mudit.piingochatservice.controller;

import com.mudit.piingochatservice.dto.MessageResponse;
import com.mudit.piingochatservice.dto.SendMessageRequest;
import com.mudit.piingochatservice.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/conversations/{conversationId}/messages")
@Tag(name = "Messages", description = "Message APIs")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @Operation(summary = "Send a message", description = "Sends a message to the specified conversation.")
    public ResponseEntity<MessageResponse> sendMessage(
            @PathVariable UUID conversationId,
            @RequestBody SendMessageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessage(conversationId, request));
    }

    @GetMapping
    @Operation(summary = "Get messages", description = "Returns paginated messages for the specified conversation, oldest first.")
    public ResponseEntity<Page<MessageResponse>> getMessages(
            @PathVariable UUID conversationId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(messageService.getMessages(conversationId, pageable));
    }
}