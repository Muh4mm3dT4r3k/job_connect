package org.example.project.job;

import org.example.project.job.dto.ApplyJobDto;
import org.example.project.job.dto.JobDto;
import org.example.project.job.model.Job;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public List<JobDto> findAllJobs() {
        return jobService.findAllJobs();
    }

//    @GetMapping("/filter")
    public List<JobDto> findJobByLocation(@RequestParam String location) {
        return jobService.findJobByLocation(location);
    }

    @PostMapping("/apply")
    public void applyJob(@RequestBody ApplyJobDto applyJobDto) {
        jobService.applyJob(applyJobDto);
    }

//    @GetMapping("/filter")
    public List<JobDto> findJobByIndustry(@RequestParam String industry) {
        return jobService.findJobByIndustry(industry);
    }

//    @GetMapping("/filter")
    public List<JobDto> findJobByDate(@RequestParam LocalDateTime createdOn) {
        return jobService.findJobByDate(createdOn);
    }

//    @GetMapping("/filter")
    public List<JobDto> findJobByDateBetween(@RequestParam LocalDateTime startDate,@RequestParam LocalDateTime endDate) {
        return jobService.findJobByDateBetween(startDate, endDate);
    }

//    @GetMapping("/filter")
    public List<JobDto> findJobBySalary(@RequestParam BigDecimal budget) {
        return jobService.findJobBySalary(budget);
    }

//    @GetMapping("/filter")
    public List<JobDto> findByJobSalaryRange(@RequestParam BigDecimal startRange,@RequestParam BigDecimal endRange) {
        return jobService.findByJobSalaryRange(startRange, endRange);
    }
}
