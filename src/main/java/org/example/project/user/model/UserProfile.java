package org.example.project.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "user_profile")
@Getter
@Setter
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profile_sequence")
    @SequenceGenerator(name = "user_profile_sequence", sequenceName = "user_profile_seq", allocationSize = 1)
    private Integer id;
    private String photo;
    private String cv;
    private LocalDate birth;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_profile")
    private User user;

}
