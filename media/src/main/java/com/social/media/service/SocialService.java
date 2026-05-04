package com.social.media.service;

import com.social.media.exception.ResourceNotFoundException;
import com.social.media.model.SocialUser;
import com.social.media.repository.SocialUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SocialService {

    private final SocialUserRepository socialUserRepository;

    public SocialService(SocialUserRepository socialUserRepository) {
        this.socialUserRepository = socialUserRepository;
    }

    @Transactional(readOnly = true)
    public List<SocialUser> getAllUsers() {
        return socialUserRepository.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser) {
        return socialUserRepository.save(socialUser);
    }

    public void deleteUser(Long userId) {
        SocialUser user = getUserOrThrow(userId);
        socialUserRepository.delete(user);
    }

    private SocialUser getUserOrThrow(Long userId) {
        return socialUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
    }
}
