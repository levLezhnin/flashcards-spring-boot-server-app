package com.flashcards.version1.repository;

import com.flashcards.main.domain.Theme;
import com.flashcards.main.repository.ThemeRepository;
import com.flashcards.main.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
@DisplayName("Класс ThemeRepositoryTest")
public class ThemeRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("should insert theme")
    @Test
    void shouldInsertTheme() {

        Theme expected = Theme.builder()
                .name("new Theme")
                .user(userRepository.getById(2))
                .build();

        themeRepository.save(expected);

        Theme actual = themeRepository.getById(3);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("should update user by id")
    @Test
    void shouldUpdateThemeNameById(){

        Theme expected = Theme.builder()
                .id(1)
                .name("Goga123")
                .user(userRepository.getById(1))
                .build();

        themeRepository.updateNameById(1, "Goga123");

        Theme actual = themeRepository.getById(1);

        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getUser()).isEqualTo(expected.getUser());
    }

    @DisplayName("should get all themes by user id")
    @Test
    void shouldGetAllThemesByUserId(){

        assertThat(themeRepository.findAllByUserId(2).size()).isEqualTo(1);
    }

    @DisplayName("should get a theme by id")
    @Test
    void shouldGetThemeById(){
        Theme expected = Theme.builder()
                .id(1)
                .name("goga")
                .user(userRepository.getById(1))
                .build();

        Theme actual = themeRepository.getById(1);

        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getUser()).isEqualTo(expected.getUser());
    }

    @DisplayName("should delete a theme by id")
    @Test
    void shouldDeleteThemeById(){

        themeRepository.deleteById(1);

        assertThatThrownBy(() -> themeRepository.getById(1)).isInstanceOf(JpaObjectRetrievalFailureException.class);
    }
}
