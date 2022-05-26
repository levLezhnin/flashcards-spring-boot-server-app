package com.flashcards.main.service;

import com.flashcards.main.domain.Card;
import com.flashcards.main.repository.CardRepository;
import com.flashcards.main.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImplementation implements CardService {

    private final CardRepository cardRepository;
    private final ThemeRepository themeRepository;

    @Override
    public Card insert(int themeId, String key, String value) {
        Card card = Card.builder()
                .key(key)
                .value(value)
                .theme(themeRepository.getById(themeId))
                .build();
        return cardRepository.saveAndFlush(card);
    }

    @Override
    @Transactional
    public void update(int id, String key, String value) {
        Card existing_card = getById(id);
        if(key == null || key.equals("")){
            key = existing_card.getKey();
        }
        if(value == null || value.equals("")){
            value = existing_card.getValue();
        }
        cardRepository.updateById(id, key, value);
    }

    @Override
    public Card getById(int id) {
        return cardRepository.getById(id);
    }

    @Override
    public List<Card> getAllCardsByThemeId(int id) {
        return cardRepository.findAllByThemeId(id);
    }

    @Override
    public void deleteById(int id) {
        cardRepository.deleteById(id);
        cardRepository.flush();
    }
}
