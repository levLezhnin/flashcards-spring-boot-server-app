package com.flashcards.version1.service;

import com.flashcards.main.repository.CategoryRepository;
import com.flashcards.main.service.CardService;
import com.flashcards.main.service.CardServiceImplementation;
import com.flashcards.main.service.ThemeService;
import com.flashcards.main.service.ThemeServiceImplementation;
import com.flashcards.main.domain.Card;
import com.flashcards.main.domain.Theme;
import com.flashcards.main.repository.CardRepository;
import com.flashcards.main.repository.ThemeRepository;
import com.flashcards.main.repository.UserRepository;
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

@DisplayName("Класс CardServiceImplementationTest")
@ExtendWith(MockitoExtension.class)
public class CardServiceImplementationTest {

    private CardService cardService;
    private ThemeService themeService;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private ThemeRepository themeRepository;
    @Mock
    private UserRepository userRepository;

    private List<Card> cards;

    @BeforeEach
    void init() {
        cardService = new CardServiceImplementation(cardRepository, themeRepository);
        themeService = new ThemeServiceImplementation(themeRepository, userRepository, categoryRepository);
        cards = new ArrayList<>();

        Card expectedCard1 = Card.builder()
                .id(3)
                .key("vova")
                .value("vova")
                .theme(themeRepository.getById(2))
                .build();

        Card expectedCard2 = Card.builder()
                .id(4)
                .key("aboba")
                .value("aboba")
                .theme(themeRepository.getById(2))
                .build();

        cards.add(expectedCard1);
        cards.add(expectedCard2);
    }

    @DisplayName("должен получить карточку по id")
    @Test
    void shouldGetById() {

        Theme theme = themeService.getById(1);

        Card expected = Card.builder()
                .id(3)
                .theme(theme)
                .key("new key")
                .value("new value")
                .build();

        when(cardRepository.getById(3)).thenReturn(expected);

        Card actual = cardService.getById(3);

        assertThat(expected).isEqualTo(actual);
    }

    @DisplayName("должен получить все карточки по id темы")
    @Test
    void shouldGetCardsByThemeId() {

        when(cardRepository.findAllByThemeId(1)).thenReturn(cards);

        List<Card> expected = cardService.getAllCardsByThemeId(1);

        assertThat(expected).isEqualTo(cards);
    }
}
