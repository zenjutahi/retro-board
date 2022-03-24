package com.retro.board.scrumretroboard.repositories;

import com.retro.board.scrumretroboard.entities.Comment;
import com.retro.board.scrumretroboard.entities.CommentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CommentJpaRepository commentJpaRepository;
    @Test
    public void
    findByCreatedYearAndMonthAndDay_HappyPath_ShouldReturn1Comment() {
// Given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        testEntityManager.persist(comment);
        testEntityManager.flush();
// When
        LocalDate now = LocalDate.now();
        List<Comment> comments =
                commentJpaRepository.findByCreatedYearAndMonthAndDay(now.getYear(),
                        now.getMonth().getValue(), now.getDayOfMonth());
// Then
        assertThat(comments).hasSize(1);
        assertThat(comments.get(0)).hasFieldOrPropertyWithValue("comment",
                "Test");
    }
    @Test
    public void save_HappyPath_ShouldSave1Comment() {
// Given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
// When
        Comment saved = commentJpaRepository.save(comment);
// Then
        assertThat(testEntityManager.find(Comment.class,
                saved.getId())).isEqualTo(saved);
    }
}