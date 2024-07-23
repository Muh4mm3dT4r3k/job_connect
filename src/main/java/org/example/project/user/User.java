package org.example.project.user;

import jakarta.persistence.*;
import org.example.project.job.Job;
import org.example.project.job.SavedJob;
import org.example.project.message.Message;
import org.example.project.proposal.Proposal;

import java.time.LocalDate;
import java.util.List;

@Entity
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
    private LocalDate createdOn;

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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getCreateOn() {
        return createdOn;
    }

    public void setCreateOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

}
