package com.mudit.piingochatservice.dto;

import java.util.List;
import java.util.UUID;

public record CreateConversationRequest(UUID createdBy, List<UUID> participantIds) {}