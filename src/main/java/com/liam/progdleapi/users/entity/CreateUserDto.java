package com.liam.progdleapi.users.entity;

public record CreateUserDto(
        String username,
        String password,
        String profilePictureSrc
) {
}
