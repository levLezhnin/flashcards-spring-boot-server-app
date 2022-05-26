package com.flashcards.main.service;

import com.flashcards.main.domain.Category;
import com.flashcards.main.domain.UserFavouriteCategories;

import java.util.List;

public interface UserFavouriteCategoriesService {

    UserFavouriteCategories insert(int user_id, int category_id);

    UserFavouriteCategories getById(int id);

    List<Category> getAllCategoriesByUserId(int user_id);

    List<Integer> getAllCategoryIdsByUserId(int user_id);

    List<UserFavouriteCategories> getAllByUserId(int user_id);

    void deleteById(int id);
}
