package com.flashcards.main.rest.dto;

import com.flashcards.main.domain.User;
import com.flashcards.main.domain.UserFavouriteCategories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFavouriteCategoriesDTO {

    private int id;

    private int category_id;

    public static UserFavouriteCategories toDomainObject(UserFavouriteCategoriesDTO dto, User user) {
        return new UserFavouriteCategories(
                dto.getId(),
                user,
                dto.getCategory_id()
        );
    }

    public static UserFavouriteCategoriesDTO toDTO(UserFavouriteCategories userFavouriteCategories) {
        return new UserFavouriteCategoriesDTO(
                userFavouriteCategories.getId(),
                userFavouriteCategories.getCategory_id()
        );
    }
}
