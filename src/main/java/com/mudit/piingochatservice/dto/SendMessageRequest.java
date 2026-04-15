package com.mudit.piingochatservice.dto;

import java.util.UUID;

public record SendMessageRequest(UUID senderId, String content) {}