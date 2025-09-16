package com.auction.identity.Controller;

import com.auction.identity.Mapper.UserMapper;
import com.auction.identity.Models.Domain.UserRole;
import com.auction.identity.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(UserMapper.toResponse(userService.register(jwt)));
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
