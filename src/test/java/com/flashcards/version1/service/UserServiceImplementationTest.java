package com.flashcards.version1.service;

import com.flashcards.main.domain.Theme;
import com.flashcards.main.domain.User;
import com.flashcards.main.repository.ThemeRepository;
import com.flashcards.main.repository.UserRepository;
import com.flashcards.main.service.UserService;
import com.flashcards.main.service.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Класс UserServiceImplementationTest")
@ExtendWith(MockitoExtension.class)
public class UserServiceImplementationTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ThemeRepository themeRepository;

    @BeforeEach
    void init(){
        userService = new UserServiceImplementation(userRepository, themeRepository);
    }

    @DisplayName("должен получить пользователя по id")
    @Test
    void shouldGetUserById(){
        User expected = User.builder()
                .id(4)
                .nickname("New").build();
        when(userRepository.getById(4)).thenReturn(expected);

        User actual = userService.getById(4);

        assertThat(expected).isEqualTo(actual);
    }

    @DisplayName("должен получить пользователя по email")
    @Test
    void shouldGetUserByEmail(){
        User expected = User.builder()
                .nickname("New")
                .email("example4@gmail.com")
                .build();
        when(userRepository.getByEmail("example4@gmail.com")).thenReturn(expected);

        User actual = userService.getByEmail("example4@gmail.com");

        assertThat(expected).isEqualTo(actual);
    }
}