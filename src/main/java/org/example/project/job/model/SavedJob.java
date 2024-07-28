package org.example.project.job.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.project.user.model.User;

import java.time.LocalDateTime;

@Entity(name = "saved_job")
@Getter
@Setter
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

}
