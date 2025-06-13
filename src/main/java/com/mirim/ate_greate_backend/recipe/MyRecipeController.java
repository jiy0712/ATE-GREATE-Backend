package com.mirim.ate_greate_backend.recipe;

import com.mirim.ate_greate_backend.recipe.Recipe;
import com.mirim.ate_greate_backend.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import com.mirim.ate_greate_backend.recipe.RecipeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-recipes")
public class MyRecipeController {
    private final RecipeService recipeService;

    //MyRecipeServicce는 따로 안나누고 RecipeService에 같이 코드
    private final RecipeService myRecipeService;

    public MyRecipeController(RecipeService recipeService, RecipeService myRecipeService) {
        this.recipeService = recipeService;
        this.myRecipeService = myRecipeService;
    }

    @PostMapping
    public Recipe uploadMyRecipe(@ModelAttribute MyRecipeDto dto) throws IOException {
        return recipeService.registerMyRecipe(dto);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getMyRecipes(@RequestParam Long userId) {
        List<Recipe> recipes = myRecipeService.getMyRecipes(userId);
        return ResponseEntity.ok(recipes);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<String> deleteMyRecipe(@PathVariable Long recipeId) {
        myRecipeService.deleteMyRecipe(recipeId);
        return ResponseEntity.ok("나만의 레시피가 삭제되었습니다.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMyRecipe(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("material") String material,
            @RequestParam("text") String text,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        try {
            myRecipeService.updateMyRecipe(id, title, material, text, image);
            return ResponseEntity.ok("레시피 수정 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("레시피 수정 실패: " + e.getMessage());
        }
    }
}