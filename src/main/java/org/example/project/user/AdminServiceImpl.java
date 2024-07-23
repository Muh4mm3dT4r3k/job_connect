package org.example.project.user;

import jakarta.transaction.Transactional;
import org.example.project.job.Job;
import org.example.project.job.JobDto;
import org.example.project.job.JobRepository;
import org.example.project.job.JobStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    public AdminServiceImpl(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createEmployer(UserRegisterDto userRegisterDto) {
        User user = UserRegisterDto.from(userRegisterDto);
        user.setRole(UserRole.employer);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> findUserByRole(UserRole userRole) {

        return userRepository
                .findByRole(userRole)
                .stream()
                .map(UserDto::from)
                .toList();
    }

    @Override
    public void updateEmployer() {

    }

    @Override
    @Transactional
    public int deleteEmployer(Integer id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    @Transactional
    public int acceptJob(Integer id) {
        return jobRepository.updateJobStatus(id, JobStatus.accepted);
    }

    @Override
    @Transactional
    public int rejectJob(Integer id) {
        return jobRepository.updateJobStatus(id, JobStatus.rejected);
    }

    @Override
    public List<JobDto> findAllPendingJobs() {
        List<Job> pendingJobs = jobRepository.findAllPendingJobs();
        return pendingJobs
                .stream()
                .map(JobDto::from)
                .toList();
    }

    @Override
    public List<JobDto> findAllRejectedJobs() {
        return jobRepository
                .findAllRejectedJobs()
                .stream()
                .map(JobDto::from)
                .toList();
    }
}
