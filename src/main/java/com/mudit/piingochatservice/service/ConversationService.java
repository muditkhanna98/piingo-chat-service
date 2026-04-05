package com.mudit.piingochatservice.service;

import com.mudit.piingochatservice.dto.CreateConversationRequest;
import com.mudit.piingochatservice.entity.Conversation;
import com.mudit.piingochatservice.entity.ConversationParticipant;
import com.mudit.piingochatservice.entity.ConversationParticipantId;
import com.mudit.piingochatservice.repository.ConversationParticipantRepository;
import com.mudit.piingochatservice.exception.InvalidConversationRequestException;
import com.mudit.piingochatservice.repository.ConversationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;

    public UUID createNewConversation(CreateConversationRequest conversationRequest) {
        if (conversationRequest.participantIds() == null || conversationRequest.participantIds().isEmpty()) {
            throw new InvalidConversationRequestException("A conversation must have at least one participant.");
        }

        Conversation conversation = new Conversation();
        conversation.setCreatedBy(conversationRequest.createdBy());
        conversationRepository.save(conversation);

        List<UUID> allParticipants = new ArrayList<>(conversationRequest.participantIds());
        allParticipants.add(conversationRequest.createdBy());

        List<ConversationParticipant> participants = allParticipants.stream()
                .map(participantId -> {
                    ConversationParticipantId conversationParticipantId = new ConversationParticipantId();
                    conversationParticipantId.setConversationId(conversation.getId());
                    conversationParticipantId.setUserId(participantId);
                    return new ConversationParticipant(conversationParticipantId);
                })
                .toList();

        conversationParticipantRepository.saveAll(participants);
        return conversation.getId();
    }
}
