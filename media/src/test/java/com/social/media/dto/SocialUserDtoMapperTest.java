package com.social.media.dto;

import com.social.media.model.SocialUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SocialUserDtoMapperTest {

    @Test
    void toEntityCreatesProfileWhenDescriptionProvided() {
        SocialUserRequestDto requestDto = new SocialUserRequestDto("  demo profile  ");

        SocialUser socialUser = SocialUserDtoMapper.toEntity(requestDto);

        assertNotNull(socialUser.getSocialProfile());
        assertEquals("demo profile", socialUser.getSocialProfile().getDescription());
    }

    @Test
    void toResponseDtoReturnsNullDescriptionWhenProfileMissing() {
        SocialUser socialUser = new SocialUser();

        SocialUserResponseDto responseDto = SocialUserDtoMapper.toResponseDto(socialUser);

        assertNull(responseDto.profileDescription());
    }
}

