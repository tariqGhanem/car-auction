package com.auction.identity.Services;


import com.auction.identity.Models.Domain.UserRole;
import com.auction.identity.Models.Domain.UserStatus;
import com.auction.identity.Models.DTO.UserDtos;
import com.auction.identity.Models.User;
import com.auction.identity.Repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository users;

    @Transactional
    public User register(String username, String email , UserRole userRole) {

       // if (users.existsByEmail(email)) throw new IllegalArgumentException("Email already in use");

        var user = User.builder()
                .name(username)
                .email(email)
                .role(userRole)
                .status(UserStatus.ACTIVE)
                .build();

        return users.save(user);
    }

    public User getUserByEmail(String email) {
        return users.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public boolean deleteUserByEmail(String email) {
        users.delete(users.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found")));
        return true;
    }
}
