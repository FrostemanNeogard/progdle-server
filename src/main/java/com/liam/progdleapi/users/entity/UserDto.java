package com.liam.progdleapi.users.entity;

import java.util.UUID;

public record UserDto(
        UUID id,
        String username,
        String profilePictureSrc,
        Long score
) {
    public static UserDto from(User user) {
        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getProfilePictureSrc(),
            user.getScore()
        );
    }
}
