package com.flashcards.version1.service;

import com.flashcards.main.domain.Theme;
import com.flashcards.main.repository.CategoryRepository;
import com.flashcards.main.repository.ThemeRepository;
import com.flashcards.main.repository.UserRepository;
import com.flashcards.main.service.ThemeService;
import com.flashcards.main.service.ThemeServiceImplementation;
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

@DisplayName("Класс ThemeServiceImplementationTest")
@ExtendWith(MockitoExtension.class)
public class ThemeServiceImplementationTest {

    private ThemeService themeService;
    private UserService userService;
    @Mock
    private ThemeRepository themeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CategoryRepository categoryRepository;

    private List<Theme> themes;

    private Theme expectedTheme;

    @BeforeEach
    void init(){
        themeService = new ThemeServiceImplementation(themeRepository, userRepository, categoryRepository);
        userService = new UserServiceImplementation(userRepository, themeRepository);
        themes = new ArrayList<>();
        expectedTheme = Theme.builder()
                .id(1)
                .name("goga")
                .user(userService.getById(1))
                .build();

        themes.add(expectedTheme);
    }

    //TODO: should get theme by id
    @Test
    @DisplayName("should get theme by id")
    void shouldGetThemeById() {

        Theme actual = themeService.getById(1);

        assertThat(expectedTheme.getName()).isEqualTo(actual.getName());
    }

    @Test
    @DisplayName("should get all themes by user id")
    void shouldGetAllThemesByUserId() {

        when(themeRepository.findAllByUserId(1)).thenReturn(themes);

        List<Theme> expected = themeService.getAllByUserId(1);

        assertThat(expected).isEqualTo(themes);
    }



}
