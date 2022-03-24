package com.retro.board.scrumretroboard.services;

import com.retro.board.scrumretroboard.entities.Comment;
import com.retro.board.scrumretroboard.repositories.CommentJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CommentService.class);
    @Autowired
    private CommentJpaRepository commentJpaRepository;


    @Transactional(rollbackFor = Exception.class)
    public List<Comment> saveAll(List<Comment> comments) {
        LOGGER.info("Saving {}", comments);
        return commentJpaRepository.saveAll(comments);
    }
    public List<Comment> getAllCommentsForToday() {
        LocalDate localDate = LocalDate.now();
        return
                commentJpaRepository.findByCreatedYearAndMonthAndDay(localDate.getYear(),
                        localDate.getMonth().getValue(), localDate.getDayOfMonth());
    }
}
