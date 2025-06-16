package com.mirim.ate_greate_backend.community;

import com.mirim.ate_greate_backend.user.User;
import com.mirim.ate_greate_backend.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BulletinService {
    private final BulletinRepository bulletinRepository;
    private final UserRepository userRepository;

    public BulletinService(BulletinRepository bulletinRepository, UserRepository userRepository) {
        this.bulletinRepository = bulletinRepository;
        this.userRepository = userRepository;
    }

    public Bulletin createBulletin(Long userId, Bulletin bulletin) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        bulletin.setUser(user);
        return bulletinRepository.save(bulletin);
    }

    public List<Bulletin> getAllBulletins() {
        return bulletinRepository.findAll();
    }
}
