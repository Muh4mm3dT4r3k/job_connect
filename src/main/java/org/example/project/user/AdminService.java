package org.example.project.user;

import jakarta.transaction.Transactional;
import org.example.project.job.model.Job;
import org.example.project.job.dto.JobDto;
import org.example.project.job.repo.JobRepository;
import org.example.project.job.model.JobStatus;
import org.example.project.user.dto.UserDto;
import org.example.project.user.dto.UserRegisterDto;
import org.example.project.user.model.User;
import org.example.project.user.model.UserRole;
import org.example.project.user.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    public AdminService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    
    public void createEmployer(UserRegisterDto userRegisterDto) {
        User user = UserRegisterDto.from(userRegisterDto);
        user.setRole(UserRole.employer);
        userRepository.save(user);
    }

    
    public List<UserDto> findUserByRole(UserRole userRole) {

        return userRepository
                .findByRole(userRole)
                .stream()
                .map(UserDto::from)
                .toList();
    }

    
    public void updateEmployer() {

    }

    
    @Transactional
    public int deleteEmployer(Integer id) {
        return userRepository.deleteUserById(id);
    }

    
    @Transactional
    public int acceptJob(Integer id) {
        return jobRepository.updateJobStatus(id, JobStatus.accepted);
    }

    
    @Transactional
    public int rejectJob(Integer id) {
        return jobRepository.updateJobStatus(id, JobStatus.rejected);
    }

    
    public List<JobDto> findAllPendingJobs() {
        List<Job> pendingJobs = jobRepository.findAllPendingJobs();
        return pendingJobs
                .stream()
                .map(JobDto::from)
                .toList();
    }

    
    public List<JobDto> findAllRejectedJobs() {
        return jobRepository
                .findAllRejectedJobs()
                .stream()
                .map(JobDto::from)
                .toList();
    }
}
