package com.retro.board.scrumretroboard.services;


import com.retro.board.scrumretroboard.entities.Comment;
import com.retro.board.scrumretroboard.entities.CommentType;
import com.retro.board.scrumretroboard.repositories.CommentJpaRepository;
import com.retro.board.scrumretroboard.services.CommentService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentJpaRepository commentJpaRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void
    getAllCommentsForToday_HappyPath_ShouldReturn1Comment() {
// Given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new
                Timestamp(System.currentTimeMillis()));
        List<Comment> comments = Arrays.asList(comment);
        LocalDate now = LocalDate.now();
        when(commentJpaRepository.findByCreatedYearAndMonthAndDay(now.getYear(),
                now.getMonth().getValue(),
                now.getDayOfMonth())).thenReturn(comments);
// When
        List<Comment> actualComments =
                commentService.getAllCommentsForToday();
// Then
        verify(commentJpaRepository,
                times(1)).findByCreatedYearAndMonthAndDay(now.getYear(),
                now.getMonth().getValue(), now.getDayOfMonth());
        assertThat(comments).isEqualTo(actualComments);
    }
    @Test
    public void saveAll_HappyPath_ShouldSave2Comments() {
// Given
        Comment comment = new Comment();
        comment.setComment("Test Plus");
        comment.setType(CommentType.PLUS);
        comment.setCreatedBy("Shazin");
        comment.setCreatedDate(new
                Timestamp(System.currentTimeMillis()));
        Comment comment2 = new Comment();
        comment2.setComment("Test Star");
        comment2.setType(CommentType.STAR);
        comment2.setCreatedBy("Shahim");
        comment2.setCreatedDate(new
                Timestamp(System.currentTimeMillis()));
        List<Comment> comments = Arrays.asList(comment, comment2);
        when(commentJpaRepository.saveAll(comments)).thenReturn(comments);
// When
        List<Comment> saved = commentService.saveAll(comments);
// Then
        assertThat(saved).isNotEmpty();
        verify(commentJpaRepository, times(1)).saveAll(comments);
    }
}
