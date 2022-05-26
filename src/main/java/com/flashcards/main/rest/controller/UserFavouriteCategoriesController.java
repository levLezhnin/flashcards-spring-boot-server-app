package com.flashcards.main.rest.controller;

import com.flashcards.main.domain.UserFavouriteCategories;
import com.flashcards.main.rest.dto.CategoryDTO;
import com.flashcards.main.rest.dto.UserFavouriteCategoriesDTO;
import com.flashcards.main.service.UserFavouriteCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserFavouriteCategoriesController {

    private final UserFavouriteCategoriesService userFavouriteCategoriesService;

    @PostMapping("/user/{user_id}/add_to_favourites/{category_id}")
    public UserFavouriteCategoriesDTO createNew(@PathVariable("user_id") int user_id,
                                                @PathVariable("category_id") int category_id){
        UserFavouriteCategories userFavouriteCategories = userFavouriteCategoriesService.insert(user_id, category_id);
        return UserFavouriteCategoriesDTO.toDTO(userFavouriteCategories);
    }

    @GetMapping("/user/{user_id}/get_favourite_categories")
    public List<CategoryDTO> getAllByUserId(@PathVariable("user_id") int user_id) {
        return userFavouriteCategoriesService.getAllCategoriesByUserId(user_id).stream().map(CategoryDTO::toDTO).collect(Collectors.toList());
    }

    @DeleteMapping("user/{user_id}/remove_from_favourites/{category_id}")
    public void deleteCategoryByCategoryId(@PathVariable("user_id") Integer user_id,
            @PathVariable("category_id") Integer category_id) {
        List<Integer> ids = userFavouriteCategoriesService.getAllCategoryIdsByUserId(user_id);
        int index = 0;
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            if(id == category_id) {
                index = userFavouriteCategoriesService.getAllByUserId(user_id).get(i).getId();
                break;
            }
        }
        userFavouriteCategoriesService.deleteById(index);
    }
}
