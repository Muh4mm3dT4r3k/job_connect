package org.example.project.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.project.job.model.Job;
import org.example.project.job.model.SavedJob;
import org.example.project.message.Message;
import org.example.project.proposal.Proposal;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    @SequenceGenerator(name = "user_sequence_generator", schema = "job_connect", sequenceName = "user_seq", allocationSize = 1)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean enabled;
    private LocalDateTime createdOn;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "employer")
    private List<Job> jobs;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "jobSeeker")
    private List<Proposal> proposals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Message> receivedMessages;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<SavedJob> savedJobs;



}
