package com.mirim.ate_greate_backend.user;

import com.mirim.ate_greate_backend.user.User;
import com.mirim.ate_greate_backend.user.UserRepository;
import com.mirim.ate_greate_backend.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(User user) {
        if (userRepository.existsById(user.getId())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "회원가입 성공";
    }

    public String login(String id, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getId());
    }
}
