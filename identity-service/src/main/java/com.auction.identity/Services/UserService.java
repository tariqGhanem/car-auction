package com.auction.identity.Services;


import com.auction.identity.Models.Domain.UserRole;
import com.auction.identity.Models.Domain.UserStatus;
import com.auction.identity.Models.User;
import com.auction.identity.Repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository users;

    @Transactional
    public User register(Jwt jwt) {

        //Extract Name / Email
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");

        //Extract the role
        List<String> roles = extractRolesOptional(jwt)
                .orElseThrow(() -> new IllegalArgumentException("No roles found in JWT for auction-client"));

        String roleName = roles.getFirst();
        UserRole userRole;

        try {
            userRole = UserRole.valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + roleName);
        }

        var user = User.builder()
                .name(name)
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
//    Extract the role like this [Admin , User]
//    Result is like this :-
//    {
//        "resource_access": {
//        "auction-client": {
//            "roles": ["ADMIN", "USER"]
//        }
//    }
//    }
    @SuppressWarnings("unchecked")
    private Optional<List<String>> extractRolesOptional(Jwt jwt) {
        return Optional.ofNullable(jwt.getClaim("resource_access"))
                .filter(Map.class::isInstance)
                .map(Map.class::cast)
                .map(resourceAccess -> resourceAccess.get("auction-client"))
                .filter(Map.class::isInstance)
                .map(Map.class::cast)
                .map(client -> client.get("roles"))
                .filter(List.class::isInstance)
                .map(List.class::cast);
    }

}
