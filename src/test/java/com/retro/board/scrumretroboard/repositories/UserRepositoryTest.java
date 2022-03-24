package com.retro.board.scrumretroboard.repositories;

import com.retro.board.scrumretroboard.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Test
    public void findByUsername_HappyPath_ShouldReturn1User() throws
            Exception {
// Given
        User user = new User();
        user.setUsername("shazin");
        user.setPassword("shaz980");
        user.setRole("USER");
        testEntityManager.persist(user);
        testEntityManager.flush();
// When
        User actual = userJpaRepository.findByUsername("shazin");
// Then
        assertThat(actual).isEqualTo(user);
    }
    @Test
    public void save_HappyPath_ShouldSave1User() throws Exception {
// Given
        User user = new User();
        user.setUsername("shazin");
        user.setPassword("shaz980");
        user.setRole("USER");
// When
        User actual = userJpaRepository.save(user);
// Then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
    }
}