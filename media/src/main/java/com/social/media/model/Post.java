package com.social.media.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "social_user_id")
    @JsonIgnore
    private SocialUser socialUser;

    public Post() {
    }

    public Post(Long id, SocialUser socialUser) {
        this.id = id;
        this.socialUser = socialUser;
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

    public void setSocialUser(SocialUser socialUser) {
        if (this.socialUser == socialUser) {
            return;
        }

        SocialUser previousUser = this.socialUser;
        this.socialUser = socialUser;

        if (previousUser != null) {
            previousUser.getPosts().remove(this);
        }

        if (socialUser != null && !socialUser.getPosts().contains(this)) {
            socialUser.getPosts().add(this);
        }
    }
}
