package com.mirim.ate_greate_backend.community;

import com.mirim.ate_greate_backend.user.User;
import com.mirim.ate_greate_backend.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BulletinRepository bulletinRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          BulletinRepository bulletinRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.bulletinRepository = bulletinRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(Long bulletinId, Long userId, Comment comment) {
        Bulletin bulletin = bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        comment.setBulletin(bulletin);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByBulletinId(Long bulletinId) {
        return commentRepository.findByBulletinId(bulletinId);
    }

    public Comment increaseGood(Long commentId) {
        Comment comment = getCommentOrThrow(commentId);
        comment.setGood(comment.getGood() + 1);
        return commentRepository.save(comment);
    }

    public Comment increaseBad(Long commentId) {
        Comment comment = getCommentOrThrow(commentId);
        comment.setBad(comment.getBad() + 1);
        return commentRepository.save(comment);
    }

    private Comment getCommentOrThrow(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
    }
}
