package org.example.project.job;

import org.example.project.job.dto.ApplyJobDto;
import org.example.project.job.dto.JobDto;
import org.example.project.job.model.Job;
import org.example.project.job.repo.JobRepository;
import org.example.project.proposal.Proposal;
import org.example.project.proposal.ProposalRepository;
import org.example.project.proposal.ProposalStatus;
import org.example.project.user.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, ProposalRepository proposalRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
    }

    public List<JobDto> findAllJobs() {
        List<Job> jobs = jobRepository.findAllJobs();
        return jobs
                .stream()
                .map(JobDto::from)
                .toList();
    }

    public void applyJob(ApplyJobDto applyJobDto) {
        var job = jobRepository.findById(applyJobDto.jobId()).get();
        var jobSeeker = userRepository.findById(applyJobDto.userId()).get();
        var proposal = new Proposal();
        proposal.setJob(job);
        proposal.setJobSeeker(jobSeeker);
        proposal.setCv(jobSeeker.getUserProfile().getCv());
        proposal.setSubmittedAt(LocalDateTime.now());
        proposal.setStatus(ProposalStatus.pending);
        job.setNumberOfProposals(job.getNumberOfProposals() + 1);
        proposalRepository.save(proposal);
        jobRepository.save(job);

    }


    public List<JobDto> findJobByLocation(String location) {
        return jobRepository.findJobByLocation(location)
                .stream()
                .map(JobDto::from)
                .toList();
    }


    public List<JobDto> findJobByIndustry(String industry) {
        return jobRepository.findJobByIndustry(industry)
                .stream()
                .map(JobDto::from)
                .toList();
    }


    public List<JobDto> findJobByDate(LocalDateTime createdOn) {
        return jobRepository
                .findJobByCreatedOn(createdOn)
                .stream()
                .map(JobDto::from)
                .toList();
    }


    public List<JobDto> findJobByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return jobRepository.findJobByCreatedOnBetween(startDate, endDate)
                .stream()
                .map(JobDto::from)
                .toList();
    }


    public List<JobDto> findJobBySalary(BigDecimal budget) {
        return jobRepository.findJobByBudget(budget)
                .stream()
                .map(JobDto::from)
                .toList();
    }


    public List<JobDto> findByJobSalaryRange(BigDecimal startRange, BigDecimal endRange) {
        return jobRepository.findJobByBudgetBetween(startRange, endRange)
                .stream()
                .map(JobDto::from)
                .toList();
    }
}
