package org.example.project.proposal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.project.job.model.Job;
import org.example.project.user.model.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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
    private LocalDateTime submittedAt;


}
