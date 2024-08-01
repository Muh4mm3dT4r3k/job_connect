package org.example.project.user;


import jakarta.validation.Valid;
import org.example.project.job.dto.JobDto;
import org.example.project.user.dto.UserDto;
import org.example.project.user.dto.UserRegisterDto;
import org.example.project.user.model.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAuthority('admin')")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }



    @GetMapping("/pending-jobs")
    public List<JobDto> findAllPendingJobs() {
        return adminService.findAllPendingJobs();
    }

    @GetMapping("/rejected-jobs")
    public List<JobDto> findAllRejectedJobs() {
        return adminService.findAllRejectedJobs();
    }

    @GetMapping("/accept-job/{id}")
    public int acceptJob(@PathVariable Integer id) {
        return adminService.acceptJob(id);
    }

    @GetMapping("/reject-job/{id}")
    public int rejectJob(@PathVariable Integer id) {
        return adminService.rejectJob(id);
    }

    @PostMapping("/create-employer")
    public ResponseEntity<String> createEmployer(@RequestBody @Valid UserRegisterDto user) {
        adminService.createEmployer(user);
        return  ResponseEntity.ok("employer created");
    }

    @GetMapping("/list-users/{role}")
    public List<UserDto> listUserByRole(@PathVariable String role){
        return adminService.findUserByRole(UserRole.valueOf(role));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        adminService.deleteEmployer(id);
        return ResponseEntity.ok("user deleted");
    }
}
