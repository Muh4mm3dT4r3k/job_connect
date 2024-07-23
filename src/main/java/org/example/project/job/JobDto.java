package org.example.project.job;

import org.example.project.user.UserDto;


import java.math.BigDecimal;
import java.time.LocalDate;

public record JobDto(
        Integer id,
        JobType jobType,
        BigDecimal budget,
        String jobTitle,
        String description,
        LocalDate createdOn,
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
