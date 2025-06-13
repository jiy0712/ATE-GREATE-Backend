package com.mirim.ate_greate_backend.recipe;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "recipes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String material;

    private String text;

    private String imgurl;

    private Integer clickRecipe = 0;

    private Boolean saveRecipe = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Integer userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getClickRecipe() {
        return clickRecipe;
    }

    public void setClickRecipe(Integer clickRecipe) {
        this.clickRecipe = clickRecipe;
    }

    public Boolean getSaveRecipe() {
        return saveRecipe;
    }

    public void setSaveRecipe(Boolean saveRecipe) {
        this.saveRecipe = saveRecipe;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}