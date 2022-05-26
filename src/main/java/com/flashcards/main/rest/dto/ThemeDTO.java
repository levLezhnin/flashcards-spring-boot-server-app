package com.flashcards.main.rest.dto;

import com.flashcards.main.domain.Card;
import com.flashcards.main.domain.Category;
import com.flashcards.main.domain.Theme;
import com.flashcards.main.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeDTO {

    private int id;

    private String name;

    private List<CardDTO> cardDTOList;

    public static Theme toDomainObject(ThemeDTO themeDTO, User user, List<Card> cards, Category category) {

        return new Theme(themeDTO.getId(),
                themeDTO.getName(),
                user,
                cards,
                category);
    }

    public static ThemeDTO toDTO(Theme theme) {
        List<CardDTO> cardDTOList;
        if (theme.getCards() != null) {
            cardDTOList = theme.getCards().stream().map(CardDTO::toDTO).toList();
        } else {
            cardDTOList = new ArrayList<>();
        }
        return new ThemeDTO(theme.getId(),
                theme.getName(),
                cardDTOList);
    }

}
