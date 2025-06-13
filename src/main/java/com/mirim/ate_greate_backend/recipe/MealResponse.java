package com.mirim.ate_greate_backend.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MealResponse {
    private List<Meal> meals;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meal {
        //themealdb api (JSON -> JAVA) 받아오기
        @JsonProperty("idMeal")
        private String id;

        @JsonProperty("strMeal")
        private String title;

        @JsonProperty("strInstructions")
        private String instructions;

        @JsonProperty("strMealThumb")
        private String image;

        @JsonProperty("strIngredient1")
        private String ingredient1;

        @JsonProperty("strIngredient2")
        private String ingredient2;

        // 필요하면 더 추가
    }
}
