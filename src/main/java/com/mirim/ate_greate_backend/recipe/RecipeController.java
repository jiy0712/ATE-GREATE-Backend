package com.mirim.ate_greate_backend.recipe;

import com.mirim.ate_greate_backend.recipe.MealResponse;
import com.mirim.ate_greate_backend.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
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
}