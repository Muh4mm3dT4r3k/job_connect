package org.example.project.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.project.user.model.User;
import org.example.project.user.model.UserRole;

import java.time.LocalDateTime;

public record UserRegisterDto (
        @NotEmpty(message = "first name is required")
        @Size(min = 3, message = "should be more than 3")
        String firstName,
        @NotEmpty(message = "last name is required")
        String lastName,
        @NotEmpty(message = "email is required")
        @Email(message = "please enter a valid email")
        String email,
        @NotEmpty(message = "password is required")
        @Size(min = 8, message = "password must be more 8 chars")
        String password
) {
        public static User from(UserRegisterDto userRegisterDto) {
                User user = new User();
                user.setEmail(userRegisterDto.email);
                user.setEnabled(true);
                user.setFirstName(userRegisterDto.firstName);
                user.setLastName(userRegisterDto.lastName);
                user.setPassword(userRegisterDto.password);
                user.setCreatedOn(LocalDateTime.now());
                user.setRole(UserRole.jobseeker);
                return user;
        }
}
