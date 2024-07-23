package org.example.project.job;

import jakarta.persistence.*;
import org.example.project.proposal.Proposal;
import org.example.project.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
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
    private LocalDate createdOn;
    private Integer numberOfProposals;
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    private String location;
    private String industry;

    @ManyToOne
    @JoinColumn(name = "fk_employer_id")
    private User employer;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job", cascade = CascadeType.ALL)
    private List<Proposal> proposals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job", cascade = CascadeType.ALL)
    private List<SavedJob> savedJobs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getNumberOfProposals() {
        return numberOfProposals;
    }

    public void setNumberOfProposals(Integer numberOfProposals) {
        this.numberOfProposals = numberOfProposals;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
