package org.example.project.proposal;

import jakarta.persistence.*;
import org.example.project.job.Job;
import org.example.project.user.User;

import java.time.LocalDate;

@Entity
public class Proposal {
    @Id
    @SequenceGenerator(name = "proposal_sequence_generator", sequenceName = "proposal_seq", allocationSize = 1)
    @GeneratedValue(generator = "proposal_sequence_generator", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_jobseeker_id")
    private User jobSeeker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_id")
    private Job job;
    private String cv;
    @Enumerated(EnumType.STRING)
    private ProposalStatus status;
    private LocalDate submittedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public LocalDate getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDate submittedAt) {
        this.submittedAt = submittedAt;
    }
}
