package com.retro.board.scrumretroboard.repositories;

import com.retro.board.scrumretroboard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
