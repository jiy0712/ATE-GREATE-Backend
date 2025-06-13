package com.mirim.ate_greate_backend.user;

import com.mirim.ate_greate_backend.user.User;
import com.mirim.ate_greate_backend.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String message = userService.register(user);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        String token = userService.login(loginRequest.getId(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }

    static class LoginRequest {
        private String id;
        private String password;
    }
}
