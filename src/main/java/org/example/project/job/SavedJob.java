package org.example.project.job;

import jakarta.persistence.*;
import org.example.project.user.User;

import java.time.LocalDateTime;

@Entity(name = "saved_job")
public class SavedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "savedJob_sequence_generator")
    @SequenceGenerator(name = "savedJob_sequence_generator", schema = "job_connect", sequenceName = "saved_job_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_jobseeker_id")
    private User jobSeeker;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_job_id")
    private Job job;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime savedAt;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(User jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
