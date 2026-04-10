package com.mudit.piingochatservice.service;

import com.mudit.piingochatservice.dto.CreateConversationRequest;
import com.mudit.piingochatservice.entity.Conversation;
import com.mudit.piingochatservice.entity.ConversationParticipant;
import com.mudit.piingochatservice.entity.ConversationParticipantId;
import com.mudit.piingochatservice.repository.ConversationParticipantRepository;
import com.mudit.piingochatservice.exception.ConversationNotFoundException;
import com.mudit.piingochatservice.exception.InvalidConversationRequestException;
import com.mudit.piingochatservice.repository.ConversationRepository;
import com.mudit.piingochatservice.service.client.UserServiceClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;
    private final UserServiceClient userServiceClient;

    @Transactional
    public UUID createNewConversation(CreateConversationRequest conversationRequest) {
        if (conversationRequest.participantIds() == null || conversationRequest.participantIds().isEmpty()) {
            throw new InvalidConversationRequestException("A conversation must have a participant");
        }
        if (conversationRequest.participantIds().size() > 1) {
            throw new InvalidConversationRequestException("Only 1-1 conversations are supported for now. " +
                    "Group chats to be available soon");
        }

        Conversation conversation = new Conversation();
        conversation.setCreatedBy(conversationRequest.createdBy());

        List<UUID> allParticipants = new ArrayList<>(conversationRequest.participantIds());
        allParticipants.add(conversationRequest.createdBy());

        try {
            userServiceClient.validateUsers(allParticipants);
        } catch (FeignException.NotFound e) {
            throw new InvalidConversationRequestException("One or more users not found");
        }


        List<ConversationParticipant> participants = allParticipants.stream()
                .map(participantId -> {
                    ConversationParticipantId cpId = new ConversationParticipantId();
                    cpId.setUserId(participantId);
                    ConversationParticipant cp = new ConversationParticipant();
                    cp.setId(cpId);
                    cp.setConversation(conversation);
                    return cp;
                })
                .toList();

        conversation.setParticipants(participants);
        conversationRepository.save(conversation);
        return conversation.getId();
    }

    @Transactional
    public void deleteConversation(UUID conversationId) {
        if (!conversationRepository.existsById(conversationId)) {
            throw new ConversationNotFoundException("Conversation not found with id: " + conversationId);
        }
        conversationRepository.deleteById(conversationId);
    }
}
