package com.retro.board.scrumretroboard.services;

import com.retro.board.scrumretroboard.entities.User;
import com.retro.board.scrumretroboard.repositories.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserJpaRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void getAllCommentsForToday_HappyPath_ShouldReturn1Comment()
    {
// Given
        User user = new User();
        user.setUsername("shazin");
        user.setPassword("sha908");
        user.setRole("USER");
        when(userRepository.findByUsername("shazin")).thenReturn(user);
// When
        UserDetails actual = userService.loadUserByUsername("shazin");
// Then
        verify(userRepository, times(1)).findByUsername("shazin");
    }
}
