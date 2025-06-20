package com.mirim.ate_greate_backend.material;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByUserId(Long userId);
}