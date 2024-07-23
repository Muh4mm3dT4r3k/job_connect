package org.example.project.message;

public record MessageRequestDto(
        Integer senderId,
        Integer receiverId,
        String messageText
){
}
