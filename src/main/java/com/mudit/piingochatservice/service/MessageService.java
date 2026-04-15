package com.mudit.piingochatservice.service;

import com.mudit.piingochatservice.dto.MessageResponse;
import com.mudit.piingochatservice.dto.SendMessageRequest;
import com.mudit.piingochatservice.entity.Conversation;
import com.mudit.piingochatservice.entity.Message;
import com.mudit.piingochatservice.exception.ConversationNotFoundException;
import com.mudit.piingochatservice.repository.ConversationRepository;
import com.mudit.piingochatservice.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    @Transactional
    public MessageResponse sendMessage(UUID conversationId, SendMessageRequest request) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ConversationNotFoundException("Conversation not found with id: " + conversationId));

        Message message = new Message();
        message.setConversation(conversation);
        message.setSenderId(request.senderId());
        message.setContent(request.content());

        return MessageResponse.from(messageRepository.save(message));
    }

    @Transactional
    public Page<MessageResponse> getMessages(UUID conversationId, Pageable pageable) {
        if (!conversationRepository.existsById(conversationId)) {
            throw new ConversationNotFoundException("Conversation not found with id: " + conversationId);
        }
        return messageRepository.findByConversation_IdOrderBySentAtAsc(conversationId, pageable)
                .map(MessageResponse::from);
    }
}