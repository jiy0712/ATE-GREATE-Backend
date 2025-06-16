package com.mirim.ate_greate_backend.community;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByBulletinId(Long BulletinId);
}
