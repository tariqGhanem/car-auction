package com.auction.identity.Controller;

import com.auction.identity.Mapper.UserMapper;
import com.auction.identity.Models.DTO.UserDtos;
import com.auction.identity.Models.Domain.UserRole;
import com.auction.identity.Services.UserService;
import jakarta.validation.Valid;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt) {

        Map<String, Object> ra = jwt.getClaim("resource_access");
        Map<String, Object> client = ra == null ? null : (Map<String, Object>) ra.get("auction-client");
        List<String> roles = client == null ? List.of() : (List<String>) client.get("roles");
        String email =jwt.getClaim("email");
        String name =jwt.getClaim("name");
        System.out.println(email + name + roles);

        return ResponseEntity.ok(userService.register(name , email , UserRole.valueOf(roles.getFirst())));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt) {
        var u = userService.getUserByEmail(jwt.getClaimAsString("email"));
        return ResponseEntity.ok(UserMapper.toResponse(u));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt) {
        return ResponseEntity.ok("true");
    }

}
