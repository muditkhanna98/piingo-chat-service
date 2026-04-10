package com.mudit.piingochatservice.exception;

import com.mudit.piingochatservice.dto.ErrorResponse;
import com.mudit.piingochatservice.exception.ConversationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidConversationRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidConversationRequest(InvalidConversationRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), Instant.now()));
    }

    @ExceptionHandler(ConversationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleConversationNotFound(ConversationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Instant.now()));
    }
}