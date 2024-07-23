package org.example.project.user;

import org.example.project.job.JobDto;

import java.util.List;

public interface AdminService {
    void createEmployer(UserRegisterDto userRegisterDto);
    List<UserDto> findUserByRole(UserRole userRole);
    void updateEmployer();
    int deleteEmployer(Integer id);
    int acceptJob(Integer id);
    int rejectJob(Integer id);
    List<JobDto> findAllPendingJobs();
    List<JobDto> findAllRejectedJobs();
}
