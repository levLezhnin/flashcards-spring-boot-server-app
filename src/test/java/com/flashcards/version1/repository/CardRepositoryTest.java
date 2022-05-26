package com.flashcards.version1.repository;

import com.flashcards.main.repository.CardRepository;
import com.flashcards.main.repository.ThemeRepository;
import com.flashcards.main.domain.Card;
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
@DisplayName("Класс CardRepositoryTest")
public class CardRepositoryTest {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private CardRepository cardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("should insert card")
    @Test
    void shouldInsertCard() {

        Card expected = Card.builder()
                .key("goga222")
                .value("goga222")
                .theme(themeRepository.getById(2))
                .build();

        cardRepository.save(expected);

        Card actual = cardRepository.getById(5);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("should update card by id")
    @Test
    void shouldUpdateCardById(){

        Card expected = Card.builder()
                .id(1)
                .key("goga222")
                .value("goga222")
                .theme(themeRepository.getById(1))
                .build();

        cardRepository.updateById(1, "goga222", "goga222");

        Card actual = cardRepository.getById(1);

        assertThat(actual.getKey()).isEqualTo(expected.getKey());
        assertThat(actual.getValue()).isEqualTo(expected.getValue());
        assertThat(actual.getTheme()).isEqualTo(expected.getTheme());
    }

    @DisplayName("should get all cards by theme id")
    @Test
    void shouldGetAllCardsByThemeId(){

        assertThat(cardRepository.findAllByThemeId(1).size()).isEqualTo(2);
    }

    @DisplayName("should get a card by id")
    @Test
    void shouldGetCardById(){
        Card expected = Card.builder()
                .id(1)
                .key("goga")
                .value("goga")
                .theme(themeRepository.getById(1))
                .build();

        Card actual = cardRepository.getById(1);

        assertThat(actual.getKey()).isEqualTo(expected.getKey());
        assertThat(actual.getValue()).isEqualTo(expected.getValue());
        assertThat(actual.getTheme()).isEqualTo(expected.getTheme());
    }

    @DisplayName("should delete a card by id")
    @Test
    void shouldDeleteCardById(){

        cardRepository.deleteById(1);

        assertThatThrownBy(() -> cardRepository.getById(1)).isInstanceOf(JpaObjectRetrievalFailureException.class);
    }
}
