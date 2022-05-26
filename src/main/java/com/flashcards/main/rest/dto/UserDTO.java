package com.flashcards.main.rest.dto;

import com.flashcards.main.domain.Theme;
import com.flashcards.main.domain.User;
import com.flashcards.main.domain.UserFavouriteCategories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    private String nickname;

    private String email;

    private String password;

    private List<ThemeDTO> themeDTOList;

    private List<UserFavouriteCategoriesDTO> favourites;

    public static User toDomainObject(UserDTO userDTO, List<Theme> themes, List<UserFavouriteCategories> favourites) {

        return new User(userDTO.getId(),
                userDTO.getNickname(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                themes,
                favourites);
    }

    public static UserDTO toDTO(User user) {
        List<ThemeDTO> themeDTOList;
        List<UserFavouriteCategoriesDTO> favourites;
        if (user.getThemes() != null) {
            themeDTOList = user.getThemes().stream().map(ThemeDTO::toDTO).toList();
        } else {
            themeDTOList = new ArrayList<>();
        }
        if (user.getFavourite_categories() != null) {
            favourites = user.getFavourite_categories().stream().map(UserFavouriteCategoriesDTO::toDTO).collect(Collectors.toList());
        } else {
            favourites = new ArrayList<>();
        }
        return new UserDTO(user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getPassword(),
                themeDTOList,
                favourites
                );
    }
}
