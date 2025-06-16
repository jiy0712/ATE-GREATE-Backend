package com.mirim.ate_greate_backend.community;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create/{bulletinId}/{userId}")
    public ResponseEntity<Comment> createComment(
            @PathVariable Long bulletinId,
            @PathVariable Long userId,
            @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(bulletinId, userId, comment));
    }

    @GetMapping("/bulletin/{bulletinId}")
    public ResponseEntity<List<Comment>> getCommentsByBulletin(@PathVariable Long bulletinId) {
        return ResponseEntity.ok(commentService.getCommentsByBulletinId(bulletinId));
    }

    @PatchMapping("/{commentId}/good")
    public ResponseEntity<Comment> increaseGood(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.increaseGood(commentId));
    }

    @PatchMapping("/{commentId}/bad")
    public ResponseEntity<Comment> increaseBad(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.increaseBad(commentId));
    }
}
