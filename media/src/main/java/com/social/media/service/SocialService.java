package com.social.media.service;

import com.social.media.model.SocialUser;
import com.social.media.repository.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialService {

    private final SocialUserRepository socialUserRepository;
    
    public List<SocialUser> getAllUsers() {
        return socialUserRepository.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser) {
        return socialUserRepository.save(socialUser);
    }
}
