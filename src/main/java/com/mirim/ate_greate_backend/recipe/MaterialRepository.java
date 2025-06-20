package com.mirim.ate_greate_backend.recipe;

import com.mirim.ate_greate_backend.material.Material;
import com.mirim.ate_greate_backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByUserUserId(Long userId);
}
