package com.auction.identity.Mapper;

import com.auction.identity.Models.DTO.UserDtos;
import com.auction.identity.Models.User;

public class UserMapper {
    public static UserDtos.UserResponse toResponse(User u) {
        return new UserDtos.UserResponse(
                u.getId().toString(), u.getName(), u.getEmail(),
                u.getRole().name(), u.getStatus().name(),
                u.getCreatedAt().toString(), u.getUpdatedAt().toString()
        );
    }
}
