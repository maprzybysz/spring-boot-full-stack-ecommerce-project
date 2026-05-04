package com.social.media;

import com.social.media.model.Post;
import com.social.media.model.SocialGroup;
import com.social.media.model.SocialProfile;
import com.social.media.model.SocialUser;
import com.social.media.repository.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    private final SocialUserRepository userRepository;

    public DataInitializer(SocialUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            if (userRepository.count() > 0) {
                return;
            }

            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            user1.addGroup(group1);
            user2.addGroup(group1);
            user2.addGroup(group2);
            user3.addGroup(group2);

            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            user1.addPost(post1);
            user2.addPost(post2);
            user3.addPost(post3);

            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            user1.setSocialProfile(profile1);
            user2.setSocialProfile(profile2);
            user3.setSocialProfile(profile3);

            userRepository.saveAll(List.of(user1, user2, user3));
        };
    }
}
