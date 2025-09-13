package com.auction.identity.Models.DTO;


import com.auction.identity.Models.Domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDtos {

    @NotBlank @Size(min=2, max=100) String name;
    @Email @NotBlank String email;
    UserRole role ;// optional, defaults to BUYER if null

    public UserDtos(String name, String email, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.role = userRole;
    }


    public record RegisterRequest(
            @NotBlank @Size(min=2, max=100) String name,
            @Email @NotBlank String email,
            UserRole role // optional, defaults to BUYER if null
    ) {}

    public record LoginRequest(
            @Email @NotBlank String email,
            @NotBlank String password
    ) {}

    public record UserResponse(
            String id, String name, String email, String role, String status,
            String createdAt, String updatedAt
    ){}
}
