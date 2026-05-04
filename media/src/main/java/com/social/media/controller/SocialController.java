package com.social.media.controller;

import com.social.media.dto.SocialUserDtoMapper;
import com.social.media.dto.SocialUserRequestDto;
import com.social.media.dto.SocialUserResponseDto;
import com.social.media.service.SocialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/social/users")
public class SocialController {

    private final SocialService socialService;

    public SocialController(SocialService socialService) {
        this.socialService = socialService;
    }

    @GetMapping
    public ResponseEntity<List<SocialUserResponseDto>> getUsers() {
        List<SocialUserResponseDto> users = socialService.getAllUsers()
                .stream()
                .map(SocialUserDtoMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<SocialUserResponseDto> saveUser(@RequestBody SocialUserRequestDto requestDto) {
        return ResponseEntity.status(201)
                .body(SocialUserDtoMapper.toResponseDto(socialService.saveUser(SocialUserDtoMapper.toEntity(requestDto))));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        socialService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
