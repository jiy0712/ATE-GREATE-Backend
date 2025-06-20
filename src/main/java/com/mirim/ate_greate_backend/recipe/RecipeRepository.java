package com.mirim.ate_greate_backend.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllByOrderByClickRecipeDesc(); //인기(조회수)순으로 정렬
    List<Recipe> findBySaveRecipeTrue(); //즐겨찾기순으로 정렬
    List<Recipe> findAllByUserId(Long userId); //등록한 레시피 조회
    List<Recipe> findByMaterialContaining(String keyword);
    List<Recipe> findByCategory(String category);
}