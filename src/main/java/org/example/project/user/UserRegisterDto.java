package org.example.project.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRegisterDto (
        @NotEmpty(message = "first name is required")
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
                user.setCreateOn(LocalDate.now());
                user.setRole(UserRole.jobseeker);
                return user;
        }
}
