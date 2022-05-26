package com.flashcards.main.service;

import com.flashcards.main.domain.Card;

import java.util.List;

public interface CardService {

    Card insert(int themeId, String key, String value);

    Card getById(int id);

    void update(int id, String key, String value);

    List<Card> getAllCardsByThemeId(int id);

    void deleteById(int id);
}
