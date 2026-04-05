package com.mudit.piingochatservice.exception;

public class InvalidConversationRequestException extends RuntimeException {
    public InvalidConversationRequestException(String message) {
        super(message);
    }
}
