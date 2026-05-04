package com.social.media.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_user_id")
    @JsonIgnore
    private SocialUser socialUser;

    private String description;

    public void setSocialUser(SocialUser socialUser) {
        if (this.socialUser == socialUser) {
            return;
        }

        SocialUser previousUser = this.socialUser;
        this.socialUser = socialUser;

        if (previousUser != null && previousUser.getSocialProfile() == this) {
            previousUser.setSocialProfile(null);
        }

        if (socialUser != null && socialUser.getSocialProfile() != this) {
            socialUser.setSocialProfile(this);
        }
    }
}
