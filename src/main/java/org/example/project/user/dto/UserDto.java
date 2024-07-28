package org.example.project.user.dto;



import org.example.project.user.model.User;
import org.example.project.user.model.UserRole;

import java.time.LocalDateTime;

public record UserDto (
        Integer id,
        String firstName,
        String lastName,
        String email,
        UserRole role,
        boolean enabled,
        LocalDateTime createOn
) {

    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.isEnabled(),
                user.getCreatedOn()
        );
    }

}
