package com.flashcards.main.rest.dto;

import com.flashcards.main.domain.Category;
import com.flashcards.main.domain.Theme;
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
public class CategoryDTO {

    private int id;

    private String name;

    private List<ThemeDTO> themes_in_category;

    public static Category toDomainObject(CategoryDTO categoryDTO, List<Theme> themes_in_category) {
        return new Category(categoryDTO.getId(),
                categoryDTO.getName(),
                themes_in_category);
    }

    public static CategoryDTO toDTO(Category category) {
        List<ThemeDTO> themes_in_category;

        if (category.getThemes_in_category() != null) {
             themes_in_category = category.getThemes_in_category().stream().map(ThemeDTO::toDTO).toList();
        } else {
            themes_in_category = new ArrayList<>();
        }
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                themes_in_category
        );
    }
}
