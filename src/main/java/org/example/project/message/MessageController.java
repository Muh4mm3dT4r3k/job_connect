package org.example.project.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/chat")
public class MessageController {

    @PostMapping("/send")
    public void sendMessage(MessageRequestDto messageRequestDto) {

    }

    @GetMapping("/receive")
    public MessageResponseDto receiveMessage() {
        return new MessageResponseDto("test", Instant.now());
    }
}
