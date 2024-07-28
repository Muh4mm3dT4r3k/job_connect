package org.example.project.job.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.project.proposal.Proposal;
import org.example.project.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_sequence_generator")
    @SequenceGenerator(name = "job_sequence_generator", schema = "job_connect", sequenceName = "job_seq", allocationSize = 1)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    private BigDecimal budget;
    private String jobTitle;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDateTime createdOn;
    private Integer numberOfProposals;
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    private String location;
    private String industry;
    private boolean available;
    @ManyToOne
    @JoinColumn(name = "fk_employer_id")
    private User employer;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job", cascade = CascadeType.ALL)
    private List<Proposal> proposals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job", cascade = CascadeType.ALL)
    private List<SavedJob> savedJobs;


}
