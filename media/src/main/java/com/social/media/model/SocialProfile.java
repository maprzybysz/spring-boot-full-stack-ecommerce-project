package com.social.media.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_user_id")
    @JsonIgnore
    private SocialUser socialUser;

    private String description;

    public SocialProfile() {
    }

    public SocialProfile(Long id, SocialUser socialUser, String description) {
        this.id = id;
        this.socialUser = socialUser;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialUser getSocialUser() {
        return socialUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
