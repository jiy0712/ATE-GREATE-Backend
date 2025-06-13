package com.mirim.ate_greate_backend.recipe;

import com.mirim.ate_greate_backend.recipe.MealResponse;
import com.mirim.ate_greate_backend.recipe.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    //프론트의 레시피 요청 받기

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/search")
    public MealResponse searchRecipe(@RequestParam String name) {
        return recipeService.searchRecipeByName(name);
    }

    // 레시피 클릭 시 조회수 증가
    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipeById(id);
    }

    // 인기 레시피 리스트 조회
    @GetMapping("/popular")
    public List<Recipe> getPopularRecipes() {
        return recipeService.getPopularRecipes();
    }

    // 전체 레시피 조회
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // 재료명으로 검색
    @GetMapping("/searchMaterial")
    public List<Recipe> searchByMaterial(@RequestParam String keyword) {
        return recipeService.searchByMaterial(keyword);
    }

    // 한식 / 일식 / 중식 / 양식 나누기
    @GetMapping("/category")
    public ResponseEntity<List<Recipe>> getRecipesByCategory(@RequestParam String type) {
        List<Recipe> recipes = recipeService.getRecipesByCategory(type.toUpperCase());
        return ResponseEntity.ok(recipes);
    }

}