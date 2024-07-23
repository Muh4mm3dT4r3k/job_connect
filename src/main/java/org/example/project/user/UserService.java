package org.example.project.user;

import org.example.project.job.JobDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<JobDto> findAllJobs();
    void applyJob(Integer jobId);
    JobDto findJobByLocation(String location);
    JobDto findJobByIndustry(String industry);
    JobDto findJobByDate(LocalDate createdOn);
    JobDto findJobByDateBetween(LocalDate startDate, LocalDate endDate);
    JobDto findJobBySalary(BigDecimal budget);
    JobDto findByJobSalaryRange(BigDecimal startRange, BigDecimal endRange);
    void registerUser(UserRegisterDto userRegisterDto);
    void updateUserProfile(UpdateProfileDto updateProfileDto);
}
