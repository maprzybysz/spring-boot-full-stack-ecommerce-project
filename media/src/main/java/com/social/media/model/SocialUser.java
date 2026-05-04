package com.social.media.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "socialUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            joinColumns = @JoinColumn(name = "social_user_id"),
            inverseJoinColumns = @JoinColumn(name = "social_group_id")
    )
    private Set<SocialGroup> socialGroups = new HashSet<>();
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocialUser that)) {
            return false;
        }
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        if (this.socialProfile == socialProfile) {
            return;
        }
        SocialProfile previousProfile = this.socialProfile;
        this.socialProfile = socialProfile;

        if (previousProfile != null && previousProfile.getSocialUser() == this) {
            previousProfile.setSocialUser(null);
        }
        if (socialProfile != null && socialProfile.getSocialUser() != this) {
            socialProfile.setSocialUser(this);
        }
    }

    public void addPost(Post post) {
        if (post == null) {
            return;
        }
        posts.add(post);
        if (post.getSocialUser() != this) {
            post.setSocialUser(this);
        }
    }

    public void removePost(Post post) {
        if (post == null) {
            return;
        }
        posts.remove(post);
        if (post.getSocialUser() == this) {
            post.setSocialUser(null);
        }
    }

    public void addGroup(SocialGroup group) {
        if (group == null) {
            return;
        }
        socialGroups.add(group);
        if (!group.getSocialUsers().contains(this)) {
            group.getSocialUsers().add(this);
        }
    }

    public void removeGroup(SocialGroup group) {
        if (group == null) {
            return;
        }
        socialGroups.remove(group);
        if (group.getSocialUsers().contains(this)) {
            group.getSocialUsers().remove(this);
        }
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setSocialGroups(Set<SocialGroup> socialGroups) {
        this.socialGroups = socialGroups;
    }
}
