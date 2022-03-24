package com.retro.board.scrumretroboard.repositories;

import com.retro.board.scrumretroboard.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE year(c.createdDate) = ?1 AND\n" +
            "month(c.createdDate) = ?2 AND\n" +
            "day(c.createdDate) = ?3")
    List<Comment> findByCreatedYearAndMonth(int year, int month, int day);
}
