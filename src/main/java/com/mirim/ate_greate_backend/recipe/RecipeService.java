package com.mirim.ate_greate_backend.recipe;

import com.mirim.ate_greate_backend.material.Material;
import com.mirim.ate_greate_backend.recipe.MealResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class RecipeService {

    public List<Recipe> getMyRecipes(Long userId) {
        return recipeRepository.findAllByUserId(userId);
    }
    private final RecipeRepository recipeRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${file.upload-dir}")
    private String uploadDir;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public MealResponse searchRecipeByName(String name) {
        // 외부 api 호출
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + name;
        return restTemplate.getForObject(url, MealResponse.class);
    }

    // 레시피 상세 조회 시 클릭 수 증가
    public Recipe getRecipeById(Integer id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("레시피가 존재하지 않습니다."));
        recipe.setClickRecipe(recipe.getClickRecipe() + 1);
        return recipeRepository.save(recipe);
    }

    // 인기 레시피 목록 조회 (조회수 기준 내림차순)
    public List<Recipe> getPopularRecipes() {
        return recipeRepository.findAllByOrderByClickRecipeDesc();
    }

    // 저장 상태 변경 (true/false)
    public Recipe toggleSaveRecipe(int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다"));
        recipe.setSaveRecipe(true);
        return recipeRepository.save(recipe);
    }

    // 저장된 레시피만 조회
    public List<Recipe> getSavedRecipes() {
        return recipeRepository.findBySaveRecipeTrue();
    }
    public Recipe registerMyRecipe(MyRecipeDto dto) throws IOException {
        MultipartFile image = dto.getImage();

        // 고유한 파일명 생성
        String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String filePath = uploadDir + File.separator + filename;

        // 이미지 파일 저장
        File dest = new File(filePath);
        image.transferTo(dest);

        // DB 저장
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setMaterial(dto.getMaterial());
        recipe.setText(dto.getText());
        recipe.setImgurl("/images/" + filename);
        recipe.setClickRecipe(0);
        recipe.setSaveRecipe(false);
        recipe.setCreatedAt(LocalDateTime.now());

        return recipeRepository.save(recipe);
    }

    public void deleteMyRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId.intValue());
    }

    public void updateMyRecipe(Long recipeId, String title, String material, String text, MultipartFile image) throws IOException {
        Recipe recipe = recipeRepository.findById(Math.toIntExact(recipeId))
                .orElseThrow(() -> new IllegalArgumentException("해당 레시피가 존재하지 않습니다."));

        recipe.setTitle(title);
        recipe.setMaterial(material);
        recipe.setText(text);

        // 이미지가 새로 들어온 경우에만 저장 및 교체
        if (image != null && !image.isEmpty()) {
            String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get("images", filename);
            Files.createDirectories(filePath.getParent()); // 폴더 없을 시 생성
            Files.write(filePath, image.getBytes());

            recipe.setImgurl("/images/" + filename);
        }

        recipeRepository.save(recipe);
    }

    // 모든 레시피 (API + 나만의) 조회
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // 재료명으로 검색
    public List<Recipe> searchByMaterial(String keyword) {
        return recipeRepository.findByMaterialContaining(keyword);
    }

    public List<Recipe> getRecipesByCategory(String category) {
        return recipeRepository.findByCategory(category);
    }
}
