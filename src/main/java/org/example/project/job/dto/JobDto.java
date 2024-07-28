package org.example.project.job.dto;

import org.example.project.job.model.Job;
import org.example.project.job.model.JobType;
import org.example.project.user.dto.UserDto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record JobDto(
        Integer id,
        JobType jobType,
        BigDecimal budget,
        String jobTitle,
        String description,
        LocalDateTime createdOn,
        Integer numberOfProposal,
        String location,
        String industry,
        UserDto employer
) {

    public static JobDto from(Job job) {
        return new JobDto(
                job.getId(),
                job.getJobType(),
                job.getBudget(),
                job.getJobTitle(),
                job.getDescription(),
                job.getCreatedOn(),
                job.getNumberOfProposals(),
                job.getLocation(),
                job.getIndustry(),
                UserDto.from(job.getEmployer())
        );
    }
}
