package org.example.project.message;

import java.time.Instant;
import java.time.LocalDate;

public record MessageResponseDto (
        String messageText,
        Instant sentAt
){
}
