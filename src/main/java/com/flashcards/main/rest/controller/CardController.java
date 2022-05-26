package com.flashcards.main.rest.controller;

import com.flashcards.main.domain.Card;
import com.flashcards.main.rest.dto.CardDTO;
import com.flashcards.main.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/card/new/")
    public CardDTO createNewCard(@RequestParam int themeId,
                                 @RequestParam String key,
                                 @RequestParam String value) {

        Card card = cardService.insert(themeId, key, value);

        return CardDTO.toDTO(card);
    }

    @PostMapping("/card/{id}/")
    public void updateCard(@PathVariable int id,
                           @RequestParam String key,
                           @RequestParam String value) {
        cardService.update(id, key, value);
    }

    @GetMapping("/theme/{id}/cards")
    public List<CardDTO> getAllCardsByThemeId(@PathVariable int id) {
        return cardService.getAllCardsByThemeId(id).stream().map(CardDTO::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/card/{id}")
    public CardDTO getCardById(@PathVariable int id) {
        return CardDTO.toDTO(cardService.getById(id));
    }

    @DeleteMapping("/card/{id}")
    public void deleteThemeById(@PathVariable int id) {
        cardService.deleteById(id);
    }
}
