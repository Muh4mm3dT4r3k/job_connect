package org.example.project.user.dto;

import org.springframework.web.multipart.MultipartFile;

public record UpdateProfileDto(
        String userId,
        MultipartFile photo,
        MultipartFile cv,
        String birth
) {
}
