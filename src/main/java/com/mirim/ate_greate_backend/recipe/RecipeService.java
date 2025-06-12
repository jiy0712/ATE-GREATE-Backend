package com.mirim.ate_greate_backend.recipe;

import com.mirim.ate_greate_backend.recipe.MealResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeService {

    private final RestTemplate restTemplate = new RestTemplate();

    public MealResponse searchRecipeByName(String name) {
        //외부 api 호출
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + name;
        return restTemplate.getForObject(url, MealResponse.class);
    }
}
