package com.mirim.ate_greate_backend.material;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Material> createMaterial(
            @PathVariable Long userId,
            @RequestBody Material material) {
        return ResponseEntity.ok(materialService.createMaterial(userId, material));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Material>> getMaterialsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(materialService.getMaterialsByUser(userId));
    }
}
