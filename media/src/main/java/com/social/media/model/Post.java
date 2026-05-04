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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "social_user_id")
    @JsonIgnore
    private SocialUser socialUser;
    
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
