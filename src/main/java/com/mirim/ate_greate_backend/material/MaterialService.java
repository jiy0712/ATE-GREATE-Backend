package com.mirim.ate_greate_backend.material;

import com.mirim.ate_greate_backend.user.User;
import com.mirim.ate_greate_backend.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final UserRepository userRepository;

    public MaterialService(MaterialRepository materialRepository, UserRepository userRepository) {
        this.materialRepository = materialRepository;
        this.userRepository = userRepository;
    }

    public Material createMaterial(Long userId, Material material) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        material.setUser(user);
        return materialRepository.save(material);
    }

    public List<Material> getMaterialsByUser(Long userId) {
        return materialRepository.findByUserId(userId);
    }
}
