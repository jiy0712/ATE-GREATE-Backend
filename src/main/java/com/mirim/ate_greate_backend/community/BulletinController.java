package com.mirim.ate_greate_backend.community;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bulletins")
public class BulletinController {
    private final BulletinService bulletinService;

    public BulletinController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Bulletin> createBulletin(
            @PathVariable Long userId,
            @RequestBody Bulletin bulletin) {
        Bulletin created = bulletinService.createBulletin(userId, bulletin);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Bulletin>> getAllBulletins() {
        return ResponseEntity.ok(bulletinService.getAllBulletins());
    }

    @PatchMapping("/{id}/good")
    public ResponseEntity<Bulletin> increaseGood(@PathVariable Long id) {
        return ResponseEntity.ok(bulletinService.increaseGood(id));
    }

    @PatchMapping("/{id}/bad")
    public ResponseEntity<Bulletin> increaseBad(@PathVariable Long id) {
        return ResponseEntity.ok(bulletinService.increaseBad(id));
    }

    @PatchMapping("/{id}/click")
    public ResponseEntity<Bulletin> increaseClick(@PathVariable Long id) {
        return ResponseEntity.ok(bulletinService.increaseClick(id));
    }

}
