package org.example.project.user;

import jakarta.transaction.Transactional;
import org.example.project.job.Job;
import org.example.project.job.JobDto;
import org.example.project.job.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    public UserServiceImpl(JobRepository jobRepository, UserRepository userRepository, FileStorageService fileStorageService) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<JobDto> findAllJobs() {
        List<Job> jobs = jobRepository.findAllJobs();
        return jobs
                .stream()
                .map(JobDto::from)
                .toList();
    }

    @Override
    public void applyJob(Integer jobId) {

    }

    @Override
    public JobDto findJobByLocation(String location) {
        return null;
    }

    @Override
    public JobDto findJobByIndustry(String industry) {
        return null;
    }

    @Override
    public JobDto findJobByDate(LocalDate createdOn) {
        return null;
    }

    @Override
    public JobDto findJobByDateBetween(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public JobDto findJobBySalary(BigDecimal budget) {
        return null;
    }

    @Override
    public JobDto findByJobSalaryRange(BigDecimal startRange, BigDecimal endRange) {
        return null;
    }

    @Override
    @Transactional
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = UserRegisterDto.from(userRegisterDto);
        userRepository.save(user);
    }

    @Override
    public void updateUserProfile(UpdateProfileDto updateProfileDto) {
        MultipartFile photo = updateProfileDto.photo();
        MultipartFile cv = updateProfileDto.cv();
        String userId = updateProfileDto.userId();
        String photoName = fileStorageService.storeFile(photo, "photo", userId);
        String cvName = fileStorageService.storeFile(cv, "pdf", userId);
        User user = userRepository.getReferenceById(Integer.valueOf(userId));
        UserProfile userProfile = user.getUserProfile();
        if (userProfile == null) {
            userProfile = new UserProfile();
            user.setUserProfile(userProfile);
        }

        if (!photoName.isEmpty())
            userProfile.setPhoto(photoName);

        if (!cvName.isEmpty())
            userProfile.setCv(cvName);
        userProfile.setBirth(LocalDate.parse(updateProfileDto.birth()));
        userProfile.setUser(user);

        userRepository.save(user);
    }


}
