package org.example.project.message;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.project.user.model.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq_generator")
    @SequenceGenerator(name = "message_seq_generator", sequenceName = "massage_seq", allocationSize = 1)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String messageText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_receiver_id")
    private User receiver;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime sentAt;
}
