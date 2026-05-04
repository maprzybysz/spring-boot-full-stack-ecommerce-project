package com.social.media.dto;

import com.social.media.model.SocialProfile;
import com.social.media.model.SocialUser;

public final class SocialUserDtoMapper {

    private SocialUserDtoMapper() {
    }

    public static SocialUserResponseDto toResponseDto(SocialUser socialUser) {
        String profileDescription = null;
        if (socialUser.getSocialProfile() != null) {
            profileDescription = socialUser.getSocialProfile().getDescription();
        }
        return new SocialUserResponseDto(socialUser.getId(), profileDescription);
    }

    public static SocialUser toEntity(SocialUserRequestDto requestDto) {
        SocialUser socialUser = new SocialUser();
        if (requestDto == null || requestDto.profileDescription() == null || requestDto.profileDescription().isBlank()) {
            return socialUser;
        }

        SocialProfile socialProfile = new SocialProfile();
        socialProfile.setDescription(requestDto.profileDescription().trim());
        socialUser.setSocialProfile(socialProfile);
        return socialUser;
    }
}

