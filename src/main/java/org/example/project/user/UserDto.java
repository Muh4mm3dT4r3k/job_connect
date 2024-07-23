package org.example.project.user;



import java.time.LocalDate;

public record UserDto (
        Integer id,
        String firstName,
        String lastName,
        String email,
        UserRole role,
        boolean enabled,
        LocalDate createOn
) {

    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.isEnabled(),
                user.getCreateOn()
        );
    }

}
