package org.example.project.user;

import jakarta.transaction.Transactional;
import org.example.project.user.dto.UpdateProfileDto;
import org.example.project.user.dto.UserRegisterDto;
import org.example.project.user.model.User;
import org.example.project.user.model.UserProfile;
import org.example.project.user.repo.UserRepository;
import org.example.project.utils.FileStorageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = UserRegisterDto.from(userRegisterDto);
        userRepository.save(user);
    }

    
    public void updateUserProfile(UpdateProfileDto updateProfileDto) {
        MultipartFile photo = updateProfileDto.photo();
        MultipartFile cv = updateProfileDto.cv();
        String userId = updateProfileDto.userId();
        String photoName = FileStorageUtils.storeFile(photo, "photo", userId);
        String cvName = FileStorageUtils.storeFile(cv, "pdf", userId);
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
