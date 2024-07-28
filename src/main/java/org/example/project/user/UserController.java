package org.example.project.user;

import jakarta.validation.Valid;
import org.example.project.user.dto.UpdateProfileDto;
import org.example.project.user.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        userService.registerUser(userRegisterDto);
        return ResponseEntity.ok("successfully :)");
    }

    @PostMapping("/update-profile")
    public void updateProfile(UpdateProfileDto updateProfileDto) {
        userService.updateUserProfile(updateProfileDto);
    }
}
