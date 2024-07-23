package org.example.project.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "user_profile")
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



    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
