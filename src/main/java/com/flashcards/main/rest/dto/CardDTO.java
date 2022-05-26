package com.flashcards.main.rest.dto;

import com.flashcards.main.domain.Card;
import com.flashcards.main.domain.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

    private int id;

    private String key;

    private String value;

    public static Card toDomainObject(CardDTO cardDTO, Theme theme) {

        return new Card(cardDTO.getId(),
                cardDTO.getKey(),
                cardDTO.getValue(), theme);
    }

    public static CardDTO toDTO(Card card) {

        return new CardDTO(card.getId(),
                card.getKey(),
                card.getValue());
    }
}
