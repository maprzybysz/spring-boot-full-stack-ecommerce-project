package com.social.media.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SocialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "socialGroups")
    @JsonIgnore
    private Set<SocialUser> socialUsers = new HashSet<>();

    public SocialGroup() {
    }

    public SocialGroup(Long id, Set<SocialUser> socialUsers) {
        this.id = id;
        this.socialUsers = socialUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SocialUser> getSocialUsers() {
        return socialUsers;
    }

    public void setSocialUsers(Set<SocialUser> socialUsers) {
        this.socialUsers = socialUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocialGroup that)) {
            return false;
        }
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addUser(SocialUser user) {
        if (user == null) {
            return;
        }
        socialUsers.add(user);
        if (!user.getSocialGroups().contains(this)) {
            user.getSocialGroups().add(this);
        }
    }

    public void removeUser(SocialUser user) {
        if (user == null) {
            return;
        }
        socialUsers.remove(user);
        if (user.getSocialGroups().contains(this)) {
            user.getSocialGroups().remove(this);
        }
    }
}
