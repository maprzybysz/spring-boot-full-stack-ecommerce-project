package com.social.media.model;


import jakarta.persistence.*;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "social_user_id")
    private SocialUser socialUser;
}
