package com.mirim.ate_greate_backend.user;

import com.mirim.ate_greate_backend.jwt.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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
        // loginId로 중복 검사
        if (userRepository.existsByLoginId(user.getLoginId())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }

        // 비밀번호 암호화 후 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "회원가입 성공";
    }

    public String login(String loginId, String password) {
        // loginId로 사용자 조회
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 토큰 발급
        return jwtUtil.generateToken(user.getLoginId());
    }
}
