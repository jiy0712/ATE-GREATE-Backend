package com.mirim.ate_greate_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);

    //이거는 냅둬야하냐고 물어보기
    boolean existsByNickname(String nickname);
}

