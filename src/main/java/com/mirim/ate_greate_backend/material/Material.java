package com.mirim.ate_greate_backend.material;

import com.mirim.ate_greate_backend.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 100, nullable = false)
    private String imgUrl;

    private LocalDate foodTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDate getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(LocalDate foodTime) {
        this.foodTime = foodTime;
    }
}
